package com.takaichi00.sample.quarkus.common.constant;

public enum ErrorCode {
  ISBN_INVALID("0001"),
  URL_INVALID("0002"),
  ISBN_NOTFOUND("0003"),
  REGISTER_BOOKMARK_IS_FAILED("0004"),
  GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE("0005");

  private String code;

  private ErrorCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
