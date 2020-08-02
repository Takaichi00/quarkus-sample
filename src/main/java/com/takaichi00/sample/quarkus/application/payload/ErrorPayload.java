package com.takaichi00.sample.quarkus.application.payload;

import javax.json.bind.annotation.JsonbProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorPayload {

  @JsonbProperty(value = "error_code")
  private String errorCode;

  @JsonbProperty(value = "message")
  private String message;

}
