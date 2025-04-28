package com.disoraya.sales_system.endpoint.sale.service.impl;

import com.disoraya.sales_system.endpoint.client.model.Client;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import com.disoraya.sales_system.endpoint.client.repository.ClientRepository;
import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.product.repository.ProductRepository;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDetDto;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleWithDetailsDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.param.FilterSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleSimpleView;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleView;
import com.disoraya.sales_system.endpoint.sale.mapper.SaleMapper;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail;
import com.disoraya.sales_system.endpoint.sale.model.Sale_;
import com.disoraya.sales_system.endpoint.sale.repository.SaleDetailRepository;
import com.disoraya.sales_system.endpoint.sale.repository.SaleRepository;
import com.disoraya.sales_system.endpoint.sale.repository.view.SaleViewRepository;
import com.disoraya.sales_system.endpoint.sale.service.SaleService;
import com.disoraya.sales_system.endpoint.sale.specification.FilterSaleSpec;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.InsufficientStockException;
import com.disoraya.sales_system.exception.InvalidDateException;
import com.disoraya.sales_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {
  private final SaleRepository saleRepo;
  private final SaleViewRepository saleViewRepo;
  private final SaleDetailRepository saleDetailRepo;
  private final ClientRepository clientRepo;
  private final ProductRepository productRepo;
  private final SaleMapper mapper;

  @Override
  public SaleView getById(Integer id) {
    return saleViewRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Sale with the given ID does not exist")
    );
  }

  @Override
  public Slice<SaleSimpleView> getAllFiltered(FilterSaleDto filterSaleDto, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(
        Sort.Order.desc(Sale_.SALE_DATE),
        Sort.Order.asc(Sale_.CLIENT + "." + Client_.ID_NUMBER),
        Sort.Order.desc(Sale_.ID)
    ));
    return saleViewRepo.findBy(new FilterSaleSpec(filterSaleDto), pageable);
  }

  @Override
  @Transactional
  public OutSaleDto create(InpSaleWithDetailsDto saleDto) {
    if (saleDto.getDeliveryDate() != null && saleDto.getDeliveryDate().isBefore(saleDto.getSaleDate())) {
      throw new InvalidDateException("The delivery date cannot be earlier than the sale date");
    }
    Map<Integer, InpSaleDetDto> productMap = new HashMap<>();
    saleDto.getDetails().forEach(sd -> {
      if (productMap.putIfAbsent(sd.getProductId(), sd) != null) {
        throw new DuplicateException("Duplicate product found in the sale details");
      }
    });
    String clientIdNumber = clientRepo.findIdNumberById(saleDto.getClientId()).orElseThrow(
        () -> new NotFoundException("Client with the given ID does not exist")
    );
    List<Product> products = productRepo.findByIdIn(productMap.keySet());
    if (products.size() < productMap.size()) {
      throw new NotFoundException("One or more products from the sale details do not exist");
    }

    Client clientRef = clientRepo.getReferenceById(saleDto.getClientId());
    Sale newSale = mapper.withDetailsDtoToEntity(saleDto, clientRef);
    BigDecimal total = BigDecimal.ZERO;
    for (Product p : products) {
      InpSaleDetDto sdDto = productMap.get(p.getId());
      int remainingStock = p.getQuantityStock() - sdDto.getQuantitySold();
      if (remainingStock < 0) {
        throw new InsufficientStockException(MessageFormat.format(
            "Insufficient stock available for product with ID={0}. Only {1} units are left",
            p.getId(), p.getQuantityStock()
        ));
      }
      p.setQuantityStock(remainingStock);

      total = total.add(sdDto.getUnitPriceSold().multiply(BigDecimal.valueOf(sdDto.getQuantitySold())));
      SaleDetail newDetail = SaleDetail.builder()
          .sale(newSale)
          .product(p)
          .quantitySold(sdDto.getQuantitySold())
          .unitPriceSold(sdDto.getUnitPriceSold()).build();

      newSale.addDetail(newDetail);
    }

    Sale sale = saleRepo.save(newSale);
    return mapper.entityToDto(sale, clientIdNumber, total);
  }

  @Override
  @Transactional
  public OutSaleDto updateById(Integer id, InpSaleDto saleDto) {
    Sale sale = saleRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Sale with the given ID does not exist")
    );

    Client newClientRef = null;
    String newClientIdNum = null;
    Integer newClientId = saleDto.getClientId();
    if (newClientId != null && !newClientId.equals(sale.getClient().getId())) {
      newClientIdNum = clientRepo.findIdNumberById(saleDto.getClientId()).orElseThrow(
          () -> new NotFoundException("Client with the given ID does not exist")
      );
      newClientRef = clientRepo.getReferenceById(newClientId);
    }

    mapper.entityFromDto(saleDto, newClientRef, sale);
    if (sale.getDeliveryDate() != null && sale.getDeliveryDate().isBefore(sale.getSaleDate())) {
      throw new InvalidDateException(MessageFormat.format(
          "The delivery date({0}) cannot be earlier than the sale date({1})",
          sale.getDeliveryDate(), sale.getSaleDate()
      ));
    }
    return mapper.entityToDto(sale, newClientIdNum, null);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (!saleRepo.existsById(id)) {
      throw new NotFoundException("Sale with the given ID does not exist");
    }
    saleDetailRepo.deleteSearchingBySaleId(id);
    saleRepo.deleteSearchingById(id);
  }
}
