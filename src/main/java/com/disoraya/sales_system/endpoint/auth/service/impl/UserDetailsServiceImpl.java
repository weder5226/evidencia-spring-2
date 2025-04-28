package com.disoraya.sales_system.endpoint.auth.service.impl;

import com.disoraya.sales_system.endpoint.auth.service.UserDetService;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorPwdView;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorSimpleView;
import com.disoraya.sales_system.endpoint.supervisor.repository.view.SupervisorViewRepository;
import com.disoraya.sales_system.exception.NotFoundException;
import com.disoraya.sales_system.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetService {
  private final SupervisorViewRepository supervisorViewRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    SupervisorPwdView sup = supervisorViewRepo.findWithPwdByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("Supervisor with the given email does not exist")
    );

    return entityToUserDetails(sup, sup.getPassword());
  }

  @Override
  public UserDetails loadUserById(Integer id) {
    SupervisorSimpleView sup = supervisorViewRepo.findById(id).orElseThrow(
        () -> new NotFoundException("User with the given ref in token does not exist")
    );

    return entityToUserDetails(sup, "");
  }

  private Set<GrantedAuthority> toAuthorities(Role role) {
    return Set.of(
        new SimpleGrantedAuthority("ROLE_" + role.name())
    );
  }

  private User entityToUserDetails(SupervisorSimpleView supervisor, String password) {
    return new User(
        supervisor.getId().toString(),
        password,
        supervisor.getIsEnabled(),
        true,
        true,
        true,
        toAuthorities(supervisor.getRole()));
  }
}
