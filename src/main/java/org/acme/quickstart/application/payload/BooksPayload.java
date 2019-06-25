package org.acme.quickstart.application.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wiremock.com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksPayload {

  @JsonProperty("books")
  private List<BookPayload> bookPayload;
  
}
