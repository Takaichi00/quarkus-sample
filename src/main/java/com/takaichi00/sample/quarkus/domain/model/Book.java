package com.takaichi00.sample.quarkus.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Book {
  private final String isbn;
  private final String title;
  private final Integer price;
  private final List<String> authors;
}
