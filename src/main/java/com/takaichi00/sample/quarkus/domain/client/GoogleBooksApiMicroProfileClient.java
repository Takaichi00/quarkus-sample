package com.takaichi00.sample.quarkus.domain.client;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;

public interface GoogleBooksApiMicroProfileClient {
  Book getBook(Isbn isbn);
}
