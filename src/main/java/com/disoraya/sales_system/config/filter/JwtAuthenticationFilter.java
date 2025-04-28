package com.disoraya.sales_system.config.filter;

import com.disoraya.sales_system.endpoint.auth.service.JwtService;
import com.disoraya.sales_system.endpoint.auth.service.UserDetService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver exceptionResolver;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserDetService userDetService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authToken == null || !authToken.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = authToken.substring(7);

    try {
      Integer userId = jwtService.extractSubject(token);
      UserDetails user = userDetService.loadUserById(userId);
      if (!user.isEnabled()) throw new DisabledException("User with the given ref in token is disabled");

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          userId, null, user.getAuthorities()
      );
      SecurityContextHolder.getContext().setAuthentication(auth);

      filterChain.doFilter(request, response);
    } catch (RuntimeException e) {
      var ex = exceptionResolver.resolveException(request, response, null, e);
      if (ex == null) throw e;
    }
  }
}
