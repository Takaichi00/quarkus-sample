package com.takaichi00.sample.quarkus.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Book {
  private final String isbn;
  private final String title;
  private final Integer price;
  private final List<String> author;
}
