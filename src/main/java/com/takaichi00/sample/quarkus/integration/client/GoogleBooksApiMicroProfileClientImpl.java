package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiMicroProfileClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GoogleBooksApiMicroProfileClientImpl implements GoogleBooksApiMicroProfileClient {
  @Override
  public Book getBook(Isbn isbn) {
    return null;
  }
}
