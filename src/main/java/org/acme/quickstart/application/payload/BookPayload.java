package org.acme.quickstart.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonRootName("book")
public class BookPayload {

  @JsonProperty("title")
  private String title;
  
  @JsonProperty("price")
  private Integer price;
  
}
