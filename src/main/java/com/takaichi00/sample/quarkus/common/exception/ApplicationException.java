package com.takaichi00.sample.quarkus.common.exception;

import com.takaichi00.sample.quarkus.common.constant.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final ErrorCode errorCode;

  public ApplicationException(String message, Throwable cause, ErrorCode errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public ApplicationException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
