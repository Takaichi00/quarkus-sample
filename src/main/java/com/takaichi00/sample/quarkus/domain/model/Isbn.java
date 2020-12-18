package com.takaichi00.sample.quarkus.domain.model;

import static java.util.Objects.isNull;

import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Isbn {

  private final Long isbn;

  private Isbn(Long isbn) {
    this.isbn = isbn;
  }

  public static Isbn of(Long isbn) {
    if (isNull(isbn) || isbn.toString().length() != 13) {
      throw new ApplicationException(Error.ISBN_INVALID.getErrorMessage(isbn.toString()), Error.ISBN_INVALID);
    }

    return new Isbn(isbn);

  }

  public static Isbn of(String isbn) {

    if (isNull(isbn) || isbn.length() != 13) {
      throw new ApplicationException(Error.ISBN_INVALID.getErrorMessage(isbn), Error.ISBN_INVALID);
    }

    Long convertedIsbn = null;

    try {
      convertedIsbn = Long.valueOf(isbn);
    } catch (NumberFormatException e) {
      throw new ApplicationException(Error.ISBN_INVALID.getErrorMessage(isbn), e, Error.ISBN_INVALID);
    }

    return new Isbn(convertedIsbn);

  }

  @Override
  public String toString() {
    return isbn.toString();
  }

}
