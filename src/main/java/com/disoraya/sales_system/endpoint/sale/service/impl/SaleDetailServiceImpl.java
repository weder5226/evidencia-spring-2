package com.disoraya.sales_system.endpoint.sale.service.impl;

import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.product.repository.ProductRepository;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDetWithSaleIdDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDetDto;
import com.disoraya.sales_system.endpoint.sale.mapper.SaleDetailMapper;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetailId;
import com.disoraya.sales_system.endpoint.sale.repository.SaleDetailRepository;
import com.disoraya.sales_system.endpoint.sale.repository.SaleRepository;
import com.disoraya.sales_system.endpoint.sale.service.SaleDetailService;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.InsufficientStockException;
import com.disoraya.sales_system.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleDetailServiceImpl implements SaleDetailService {
  private final SaleDetailRepository saleDetailRepo;
  private final SaleRepository saleRepo;
  private final ProductRepository productRepo;
  private final SaleDetailMapper mapper;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public OutSaleDetDto create(InpSaleDetWithSaleIdDto saleDto) {
    SaleDetailId id = new SaleDetailId(saleDto.getSaleId(), saleDto.getProductId());
    if (saleDetailRepo.existsById(id)) {
      throw new DuplicateException("Sale detail with the given ID ref already exists");
    }
    if (!saleRepo.existsById(id.getSaleId())) {
      throw new NotFoundException("Sale with the given ID does not exist");
    }
    Product product = productRepo.findById(id.getProductId()).orElseThrow(
        () -> new NotFoundException("Product with the given ID does not exist")
    );

    int remainingStock = product.getQuantityStock() - saleDto.getQuantitySold();
    if (remainingStock < 0) {
      throw new InsufficientStockException("Insufficient stock available for product with ID=" + id.getProductId());
    }
    product.setQuantityStock(remainingStock);
    
    Sale saleRef = saleRepo.getReferenceById(id.getSaleId());
    SaleDetail saleDetail = mapper.withSaleIdDtoToEntity(saleDto, saleRef, product);
    entityManager.persist(saleDetail);
    return mapper.entityToDto(saleDetail);
  }

  @Override
  @Transactional
  public void deleteById(Integer saleId, Integer productId) {
    SaleDetailId id = new SaleDetailId(saleId, productId);
    if (!saleDetailRepo.existsById(id)) {
      throw new NotFoundException("Sale detail with the given ID ref does not exist");
    }
    saleDetailRepo.deleteSearchingById(id);
  }
}
