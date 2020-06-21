package com.takaichi00.sample.quarkus.domain.repository;

import com.takaichi00.sample.quarkus.domain.model.Book;
import java.util.List;

public interface BookRepository {
  List<Book> getAllBooks();
}
