package com.takaichi00.sample.quarkus.application.domain;

import com.takaichi00.sample.quarkus.domain.model.Book;
import java.util.List;

public interface BookService {

  List<Book> getAllBooks();

}
