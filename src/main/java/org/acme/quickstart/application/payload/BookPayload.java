package org.acme.quickstart.application.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wiremock.com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPayload {

  @JsonProperty("title")
  private String title;
  
  @JsonProperty("price")
  private Integer price;
  
}
