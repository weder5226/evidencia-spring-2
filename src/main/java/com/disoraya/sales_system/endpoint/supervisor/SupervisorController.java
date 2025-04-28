package com.disoraya.sales_system.endpoint.supervisor;

import com.disoraya.sales_system.endpoint.supervisor.dto.InpSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.OutSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorSimpleView;
import com.disoraya.sales_system.endpoint.supervisor.service.SupervisorService;
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

@Tag(name = "Supervisors", description = "Controller responsible for handling supervisors")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
  private final SupervisorService supervisorService;

  @Operation(summary = "Get All Supervisors", description = "Endpoint to retrieve paginated and filtered list of supervisors")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Slice<SupervisorSimpleView> getAllByIsEnabled(
      @RequestParam(name = "enabled", defaultValue = "true") Boolean enabled,

      @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
      @RequestParam(name = "page", defaultValue = "0")
      Integer page,

      @Max(value = 100, message = ValidationMessage.MAX)
      @Min(value = 1, message = ValidationMessage.MIN)
      @RequestParam(name = "size", defaultValue = "20")
      Integer size
  ) {
    return supervisorService.getAllByIsEnabled(enabled, page, size);
  }

  @Operation(summary = "Create Supervisor", description = "Endpoint to create a new supervisor")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OutSupervisorDto create(
      @Validated(OnCreate.class) @RequestBody InpSupervisorDto supervisorDto
  ) {
    return supervisorService.create(supervisorDto);
  }

  @Operation(summary = "Update Supervisor By ID", description = "Endpoint to update an existing supervisor by its ID")
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public OutSupervisorDto updateById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer id,
      @Validated(OnUpdate.class) @RequestBody InpSupervisorDto supervisorDto
  ) {
    return supervisorService.updateById(id, supervisorDto);
  }

  @Operation(summary = "Delete Supervisor By ID", description = "Endpoint to delete an existing supervisor by its ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteById(
      @Min(value = 1, message = ValidationMessage.MIN) @PathVariable Integer id
  ) {
    supervisorService.deleteById(id);
  }
}
