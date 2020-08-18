package com.takaichi00.sample.quarkus.domain.model;

import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import lombok.EqualsAndHashCode;

import java.net.MalformedURLException;
import java.net.URL;

@EqualsAndHashCode
public class BookUrl {
  private final URL bookUrl;

  private BookUrl(URL bookUrl) {
    this.bookUrl = bookUrl;
  }

  public static BookUrl of(String url) {
    try {
      return new BookUrl(new URL(url));
    } catch (MalformedURLException e) {
      throw new ApplicationException("url is invalid:" + url, e, "0002");
    }
  }

  @Override
  public String toString() {
    return bookUrl.toString();
  }

}
