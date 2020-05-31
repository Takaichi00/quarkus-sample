package com.takaichi00.sample.quarkus.application.payload;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookPayload {
  private String isbn;
  private String title;
  private Integer price;
  private List<String> author;
}
