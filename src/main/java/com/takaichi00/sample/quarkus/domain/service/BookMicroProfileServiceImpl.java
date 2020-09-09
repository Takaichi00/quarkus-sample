package com.takaichi00.sample.quarkus.domain.service;

import com.takaichi00.sample.quarkus.application.domain.BookMicroProfileService;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiMicroProfileClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BookMicroProfileServiceImpl implements BookMicroProfileService {

  @Inject
  GoogleBooksApiMicroProfileClient googleBooksApiMicroProfileClient;

  @Override
  public Book searchBook(Isbn isbn) {
    return googleBooksApiMicroProfileClient.getBook(isbn);
  }

}
