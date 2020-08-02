package com.takaichi00.sample.quarkus.common.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final String errorCode;

  public ApplicationException(String message, Throwable cause, String errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public ApplicationException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
