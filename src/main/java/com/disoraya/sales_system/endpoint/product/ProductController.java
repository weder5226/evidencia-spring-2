package com.disoraya.sales_system.endpoint.product;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductView;
import com.disoraya.sales_system.endpoint.product.service.ProductService;
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

@Tag(name = "Products", description = "Controller responsible for handling products")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
  private final ProductService productService;

  @Operation(summary = "Get Product By ID", description = "Endpoint to retrieve an existing product by its ID")
  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ProductView getById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer id
  ) {
    return productService.getById(id);
  }

  @Operation(summary = "Get All Products", description = "Endpoint to retrieve paginated and filtered list of products")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Slice<ProductView> getAllByHasStock(
      @RequestParam(name = "available", defaultValue = "true") Boolean available,

      @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
      @RequestParam(name = "page", defaultValue = "0")
      Integer page,

      @Max(value = 100, message = ValidationMessage.MAX)
      @Min(value = 1, message = ValidationMessage.MIN)
      @RequestParam(name = "size", defaultValue = "20")
      Integer size
  ) {
    return productService.getAllByHasStock(page, size, available);
  }

  @Operation(summary = "Create Product", description = "Endpoint to create a new product")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutProductDto create(
      @Validated(OnCreate.class) @RequestBody InpProductDto productDto
  ) {
    return productService.create(productDto);
  }

  @Operation(summary = "Update Product By ID", description = "Endpoint to update an existing product by its ID")
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public OutProductDto updateById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer id,
      @Validated(OnUpdate.class) @RequestBody InpProductDto productDto
  ) {
    return productService.updateById(id, productDto);
  }

  @Operation(summary = "Delete Product By ID", description = "Endpoint to delete an existing product by its ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer id
  ) {
    productService.deleteById(id);
  }
}
