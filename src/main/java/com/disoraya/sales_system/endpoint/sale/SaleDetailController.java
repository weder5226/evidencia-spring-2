package com.disoraya.sales_system.endpoint.sale;

import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDetWithSaleIdDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDetDto;
import com.disoraya.sales_system.endpoint.sale.service.SaleDetailService;
import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sale Details", description = "Controller responsible for handling sale details")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sale-detail")
public class SaleDetailController {
  private final SaleDetailService saleDetailService;

  @Operation(summary = "Create Sale Detail", description = "Endpoint to create a new sale detail")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutSaleDetDto create(
      @Validated(OnCreate.class) @RequestBody InpSaleDetWithSaleIdDto saleDto
  ) {
    return saleDetailService.create(saleDto);
  }

  @Operation(summary = "Delete Sale By sale ID and product ID", description = "Endpoint to delete an existing sale detail its sale ID and product ID")
  @DeleteMapping("/{saleId}/{productId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer saleId,
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer productId
  ) {
    saleDetailService.deleteById(saleId, productId);
  }
}
