package com.takaichi00.sample.quarkus.common.constant;

public enum Error {
  ISBN_INVALID("0001", "isbn %s is invalid"),
  URL_INVALID("0002", "url %s is invalid:"),
  ISBN_NOTFOUND("0003", "isbn %s is not found"),
  REGISTER_BOOKMARK_IS_FAILED("0004", "register bookmark is failed."),
  GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE("0005", "Google Books API Request Failed (MicroProfile)."),
  DELETE_BOOKMARK_IS_FAILED("0005", "delete bookmark is failed.");

  private static final String PRE_STRING = "QUARKUS_SAMPLE";

  private String errorCode;
  private String message;

  private Error(String code, String message) {
    this.errorCode = code;
    this.message = message;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }

  public String dumpLogString(String errorMessageParam) {
    return PRE_STRING + "-" + errorCode + ": " + String.format(message, errorMessageParam);
  }

  public String getErrorMessage(String errorMessageParam) {
    return String.format(message, errorMessageParam);
  }
}
