package com.disoraya.sales_system.endpoint.supervisor.service.impl;

import com.disoraya.sales_system.endpoint.supervisor.dto.InpSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.OutSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorSimpleView;
import com.disoraya.sales_system.endpoint.supervisor.mapper.SupervisorMapper;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor_;
import com.disoraya.sales_system.endpoint.supervisor.repository.SupervisorRepository;
import com.disoraya.sales_system.endpoint.supervisor.repository.view.SupervisorViewRepository;
import com.disoraya.sales_system.endpoint.supervisor.service.SupervisorService;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupervisorServiceImpl implements SupervisorService {
  private final SupervisorRepository supervisorRepo;
  private final SupervisorViewRepository supervisorViewRepo;
  private final SupervisorMapper mapper;

  @Override
  public Slice<SupervisorSimpleView> getAllByIsEnabled(Boolean isEnabled, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(
        Sort.Order.asc(Supervisor_.EMAIL),
        Sort.Order.asc(Supervisor_.ID)
    ));
    return supervisorViewRepo.findByIsEnabled(isEnabled, pageable);
  }

  @Override
  @Transactional
  public OutSupervisorDto create(InpSupervisorDto supervisorDto) {
    if (supervisorRepo.existsByEmail(supervisorDto.email())) {
      throw new DuplicateException("Email is already in use by another supervisor");
    }

    Supervisor supervisor = supervisorRepo.save(mapper.dtoToEntity(supervisorDto));
    return mapper.entityToDto(supervisor);
  }

  @Override
  @Transactional
  public OutSupervisorDto updateById(Integer id, InpSupervisorDto supervisorDto) {
    Supervisor supervisor = supervisorRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Supervisor with the given ID does not exist")
    );
    String newEmail = supervisorDto.email();
    if (newEmail != null && !newEmail.equals(supervisor.getEmail()) && supervisorRepo.existsByEmail(newEmail)) {
      throw new DuplicateException("New email is already in use by another supervisor");
    }

    mapper.entityFromDto(supervisorDto, supervisor);
    return mapper.entityToDto(supervisor);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (!supervisorRepo.existsById(id)) {
      throw new NotFoundException("Supervisor with the given ID does not exist");
    }
    supervisorRepo.deleteSearchingById(id);
  }
}
