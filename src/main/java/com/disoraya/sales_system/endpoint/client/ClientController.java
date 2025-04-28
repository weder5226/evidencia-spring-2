package com.disoraya.sales_system.endpoint.client;

import com.disoraya.sales_system.endpoint.client.dto.InpClientDto;
import com.disoraya.sales_system.endpoint.client.dto.OutClientDto;
import com.disoraya.sales_system.endpoint.client.dto.param.FilterClientDto;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientSimpleView;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientView;
import com.disoraya.sales_system.endpoint.client.service.ClientService;
import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.annotation.IntegerText;
import com.disoraya.sales_system.validation.group.OnCreate;
import com.disoraya.sales_system.validation.group.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clients", description = "Controller responsible for handling clients")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
  private final ClientService clientService;

  @Operation(summary = "Get Client Name By ID", description = "Endpoint to retrieve name of an existing client by its ID")
  @GetMapping("/name/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public String getNameById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    return clientService.getNameById(id);
  }

  @Operation(summary = "Get Client By ID", description = "Endpoint to retrieve an existing client by its ID")
  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ClientView getById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    return clientService.getById(id);
  }

  @Operation(summary = "Get All Clients", description = "Endpoint to retrieve paginated and filtered list of clients")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Slice<ClientSimpleView> getAllFiltered(
      @Size(max = 90, message = ValidationMessage.SIZE)
      @RequestParam(name = "fn-prefix", required = false)
      String fnPrefix,

      @Size(max = 90, message = ValidationMessage.SIZE)
      @RequestParam(name = "ln-prefix", required = false)
      String lnPrefix,

      @IntegerText(message = ValidationMessage.INTEGER_TEXT)
      @Size(max = 18, message = ValidationMessage.SIZE)
      @RequestParam(name = "cc-prefix", required = false)
      String ccPrefix,

      @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
      @RequestParam(name = "page", defaultValue = "0")
      Integer page,

      @Max(value = 100, message = ValidationMessage.MAX)
      @Min(value = 1, message = ValidationMessage.MIN)
      @RequestParam(name = "size", defaultValue = "20")
      Integer size
  ) {
    FilterClientDto filterClientDto = FilterClientDto.builder()
        .firstNamePrefix(fnPrefix)
        .lastNamePrefix(lnPrefix)
        .idNumberPrefix(ccPrefix).build();
    return clientService.getAllFiltered(filterClientDto, page, size);
  }

  @Operation(summary = "Create Client", description = "Endpoint to create a new client")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutClientDto create(
      @Validated(OnCreate.class) @RequestBody InpClientDto clientDto
  ) {
    return clientService.create(clientDto);
  }

  @Operation(summary = "Update Client By ID", description = "Endpoint to update an existing client by its ID")
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public OutClientDto updateById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id,
      @Validated(OnUpdate.class) @RequestBody InpClientDto clientDto
  ) {
    return clientService.updateById(id, clientDto);
  }

  @Operation(summary = "Delete Client By ID", description = "Endpoint to delete an existing client by its ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable("id") Integer id
  ) {
    clientService.deleteById(id);
  }
}
