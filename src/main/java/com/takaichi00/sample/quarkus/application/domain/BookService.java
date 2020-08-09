package com.takaichi00.sample.quarkus.application.domain;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;

import java.util.List;

public interface BookService {

  List<Book> getAllBooks();

  Book getBook(Isbn isbn);

}
