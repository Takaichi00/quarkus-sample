package com.takaichi00.sample.quarkus.domain.model;

import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BookUrl {
  private final URL value;

  private BookUrl(URL value) {
    this.value = value;
  }

  public static BookUrl of(String url) {
    try {
      return new BookUrl(new URL(url));
    } catch (MalformedURLException e) {
      throw new ApplicationException(Error.URL_INVALID.getErrorMessage(url), e, Error.URL_INVALID);
    }
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
