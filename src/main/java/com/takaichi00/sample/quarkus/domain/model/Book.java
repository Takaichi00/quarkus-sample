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
  private final Isbn isbn;
  private final String title;
  private final Integer price;
  private final List<String> authors;

  public String getQueryParamString() {

    StringBuilder queryParamString = new StringBuilder();
    queryParamString.append("?isbn=");
    queryParamString.append(isbn.toString());
    queryParamString.append("&title=");
    queryParamString.append(title);

    authors.forEach(author -> {
      queryParamString.append("&author=");
      queryParamString.append(author);
    });

    return queryParamString.toString();
  }
}
