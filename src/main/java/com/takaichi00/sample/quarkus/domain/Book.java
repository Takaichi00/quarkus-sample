package com.takaichi00.sample.quarkus.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Book {
  private final String isbn;
  private final String title;
  private final Integer price;
  private final List<String> authors;
}
