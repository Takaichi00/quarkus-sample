package com.takaichi00.sample.quarkus.service;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Book {
  private final String title;
  private final String isbn;
  private final Integer price;
}
