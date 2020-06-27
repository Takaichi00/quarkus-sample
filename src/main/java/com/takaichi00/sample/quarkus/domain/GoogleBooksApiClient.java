package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import java.util.List;

public interface GoogleBooksApiClient {

  List<Book> getAllBooks(List<Isbn> isbnList);

}
