package com.takaichi00.sample.quarkus.common.exception;

import com.takaichi00.sample.quarkus.common.constant.Error;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final Error error;

  public ApplicationException(String message, Throwable cause, Error error) {
    super(message, cause);
    this.error = error;
  }

  public ApplicationException(String message, Error error) {
    super(message);
    this.error = error;
  }
}
