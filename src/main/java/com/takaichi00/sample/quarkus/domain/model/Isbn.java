package com.takaichi00.sample.quarkus.domain.model;

import static java.util.Objects.isNull;

import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Isbn {

  private Long isbn;

  private Isbn(Long isbn) {
    this.isbn = isbn;
  }

  public static Isbn of(Long isbn) {
    if (isNull(isbn) || isbn.toString().length() != 13) {
      throw new ApplicationException("isbn is invalid:" + isbn.toString(), "0001");
    }

    return new Isbn(isbn);

  }

  @Override
  public String toString() {
    return isbn.toString();
  }

}
