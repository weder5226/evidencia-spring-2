package com.disoraya.sales_system.exception;

public class InsufficientStockException extends RuntimeException {
  public InsufficientStockException(String message) {
    super(message);
  }
}
