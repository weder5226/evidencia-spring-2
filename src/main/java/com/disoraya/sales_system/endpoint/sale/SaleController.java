package com.disoraya.sales_system.endpoint.sale;

import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleWithDetailsDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.param.FilterSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleSimpleView;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleView;
import com.disoraya.sales_system.endpoint.sale.service.SaleService;
import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import com.disoraya.sales_system.validation.group.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "Sales", description = "Controller responsible for handling sales")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sale")
public class SaleController {
  private final SaleService saleService;

  @Operation(summary = "Get Sale By ID", description = "Endpoint to retrieve an existing sale by its ID")
  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public SaleView getById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    return saleService.getById(id);
  }

  @Operation(summary = "Get All Sales", description = "Endpoint to retrieve paginated and filtered list of sales")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Slice<SaleSimpleView> getAllFiltered(
      @RequestParam(name = "min-date", required = false) LocalDate minDate,
      @RequestParam(name = "max-date", required = false) LocalDate maxDate,
      @RequestParam(name = "cc-prefix", required = false) String idNumberPrefix,
      @RequestParam(name = "delivered", required = false) Boolean isDelivered,

      @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
      @RequestParam(name = "page", defaultValue = "0")
      Integer page,

      @Max(value = 100, message = ValidationMessage.MAX)
      @Min(value = 1, message = ValidationMessage.MIN)
      @RequestParam(name = "size", defaultValue = "20")
      Integer size
  ) {
    FilterSaleDto filterSaleDto = FilterSaleDto.builder()
        .minDate(minDate)
        .maxDate(maxDate)
        .idNumberPrefix(idNumberPrefix)
        .isDelivered(isDelivered).build();
    return saleService.getAllFiltered(filterSaleDto, page, size);
  }

  @Operation(summary = "Create Sale", description = "Endpoint to create a new sale")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutSaleDto create(
      @Validated(OnCreate.class) @RequestBody InpSaleWithDetailsDto saleDto
  ) {
    return saleService.create(saleDto);
  }

  @Operation(summary = "Update Sale By ID", description = "Endpoint to update an existing sale by its ID")
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public OutSaleDto updateById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id,
      @Validated(OnUpdate.class) @RequestBody InpSaleDto saleDto
  ) {
    return saleService.updateById(id, saleDto);
  }

  @Operation(summary = "Delete Sale By ID", description = "Endpoint to delete an existing sale by its ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    saleService.deleteById(id);
  }
}
