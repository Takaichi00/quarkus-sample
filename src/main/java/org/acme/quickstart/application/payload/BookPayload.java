package org.acme.quickstart.application.payload;

import javax.json.bind.annotation.JsonbProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPayload {

  @JsonbProperty("title")
  private String title;
  
  @JsonbProperty("price")
  private Integer price;
  
}
