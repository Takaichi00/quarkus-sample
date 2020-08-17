package com.takaichi00.sample.quarkus.application.payload;

import javax.json.bind.annotation.JsonbProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorPayload {

  @JsonbProperty(value = "error_code")
  private String errorCode;

  @JsonbProperty(value = "message")
  private String message;

}
