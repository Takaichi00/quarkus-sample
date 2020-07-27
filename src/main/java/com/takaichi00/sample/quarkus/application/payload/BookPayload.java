package com.takaichi00.sample.quarkus.application.payload;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookPayload {

  @JsonbProperty(value = "isbn")
  private String isbn;

  @JsonbProperty(value = "title")
  private String title;

  @JsonbProperty(value = "authors")
  private List<String> authors;

  @JsonbProperty(value = "query_param")
  private String queryParam;
}
