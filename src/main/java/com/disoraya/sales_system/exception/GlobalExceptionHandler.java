package com.disoraya.sales_system.exception;

import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ NotFoundException.class })
  public ProblemDetail handleNotFoundException(RuntimeException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler({ DuplicateException.class, InsufficientStockException.class, InvalidDateException.class })
  public ProblemDetail handleCustomException(RuntimeException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ProblemDetail handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    List<String> errors = ex.getConstraintViolations().stream()
        .map(v -> {
          String path = v.getPropertyPath().toString();
          return path.substring(path.lastIndexOf('.') + 1) + " " + v.getMessage();
        })
        .sorted()
        .toList();
    ProblemDetail res = ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST,
        "Validation Params failed");
    res.setProperty("errors", errors);
    return res;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getField() + " " + f.getDefaultMessage())
        .sorted()
        .toList();
    ProblemDetail res = ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST,
        "Validation Body failed");
    res.setProperty("errors", errors);
    return ResponseEntity.badRequest().body(res);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ProblemDetail handleAuthException(AuthenticationException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

  @ExceptionHandler(JwtException.class)
  public ProblemDetail handleJwtException(JwtException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid token");
  }

  @ExceptionHandler(DataAccessException.class)
  public ProblemDetail handleSQLException(DataAccessException ex) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST,
        "There was an issue processing your request. Please ensure the data provided is correct and that the object is not being referenced by other records.");
  }

  @ExceptionHandler(RuntimeException.class)
  public ProblemDetail handleRuntimeException(RuntimeException ex) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Unexpected error. Please check if the data entered is correct or try again later.");
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleException(Exception ex) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Service error. Please contact support if the problem persists.");
  }
}
