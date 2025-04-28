package com.disoraya.sales_system.endpoint.product;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.InpProductDetWithProdIdDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailSimpleView;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailView;
import com.disoraya.sales_system.endpoint.product.service.ProductDetailService;
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

import java.util.List;

@Tag(name = "Catalog", description = "Controller responsible for handling product details in the catalog")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class ProductDetailController {
  private final ProductDetailService productDetailService;

  @Operation(summary = "Get All Visible Catalog Products", description = "Endpoint to retrieve all the list of enabled product details in the catalog")
  @GetMapping("/all")
  @ResponseStatus(code = HttpStatus.OK)
  public List<ProductDetailView> getAllVisible() {
    return productDetailService.getAllVisible();
  }

  @Operation(summary = "Get Product Name in Catalog By ID", description = "Endpoint to retrieve the name of a product by its ID with a valid reference in the catalog")
  @GetMapping("/available/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public String getAvailableNameById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    return productDetailService.getAvailableNameById(id);
  }

  @Operation(summary = "Get Catalog Product By ID", description = "Endpoint to retrieve an existing product detail in the catalog by its ID")
  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ProductDetailView getById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    return productDetailService.getById(id);
  }

  @Operation(summary = "Get All Catalog Products", description = "Endpoint to retrieve paginated and filtered list of product details in the catalog")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Slice<ProductDetailSimpleView> getAllByIsHidden(
      @RequestParam(name = "hidden", defaultValue = "false") Boolean hidden,

      @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
      @RequestParam(name = "page", defaultValue = "0")
      Integer page,

      @Max(value = 100, message = ValidationMessage.MAX)
      @Min(value = 1, message = ValidationMessage.MIN)
      @RequestParam(name = "size", defaultValue = "20")
      Integer size
  ) {
    return productDetailService.getAllByIsHidden(hidden, page, size);
  }

  @Operation(summary = "Create Catalog Detail", description = "Endpoint to create product detail in the catalog for an existing product")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutProductDetDto create(
      @Validated(OnCreate.class) @RequestBody InpProductDetWithProdIdDto productDetailDto
  ) {
    return productDetailService.create(productDetailDto);
  }

  @Operation(summary = "Update Catalog Detail By ID", description = "Endpoint to update an existing product detail in the catalog by its ID")
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public OutProductDetDto updateById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id,
      @Validated(OnUpdate.class) @RequestBody InpProductDetDto productDetailDto
  ) {
    return productDetailService.updateById(id, productDetailDto);
  }

  @Operation(summary = "Delete Catalog Detail By ID", description = "Endpoint to delete an existing product detail in the catalog by its ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    productDetailService.deleteById(id);
  }
}
