package com.takaichi00.sample.quarkus.common;

import com.takaichi00.sample.quarkus.common.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LogFormat {
  private static final String PRE_STRING = "QUARKUS_SAMPLE";
  private final String message;
  private final ErrorCode errorCode;

  public String dumpLogString() {
    return PRE_STRING + "-" + errorCode.getCode() + ": " + message;
  }
}
