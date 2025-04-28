package com.disoraya.sales_system.validation;

public class ValidationMessage {
  public static final String DIGITS = "must have up to {integer} digits before and {fraction} digits after the decimal";
  public static final String EMAIL = "has an invalid format";
  public static final String ENUM_TEXT = "is an invalid value";
  public static final String INTEGER_TEXT = "contains non-numeric characters";
  public static final String MAX = "is greater than {value}";
  public static final String MIN = "is less than {value}";
  public static final String NO_SPACES_AT_ENDS = "should not have leading or trailing spaces";
  public static final String NOT_BLANK = "contains nothing";
  public static final String NOT_BLANK_IF_PRESENT = "contains nothing";
  public static final String NOT_EMPTY = "is empty";
  public static final String NOT_EMPTY_IF_PRESENT = "is empty";
  public static final String NOT_NULL = "is missing";
  public static final String NULL = "must not be sent";
  public static final String PAST_OR_PRESENT = "must be a date in the past or present";
  public static final String POSITIVE_OR_ZERO = "is negative";
  public static final String PATTERN_PWD = "must have uppercase, lowercase, number, and special character";
  public static final String SIZE = "content must have a length between {min} and {max}";
  public static final String SPANISH_SPACES_TEXT = "contains illegal characters";
  public static final String URL = "has an invalid format or spaces";

  private ValidationMessage() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }
}
