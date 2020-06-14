package com.takaichi00.sample.quarkus.domain;

import java.util.List;

public interface BookRepository {
  List<Book> getAllBooks();
}
