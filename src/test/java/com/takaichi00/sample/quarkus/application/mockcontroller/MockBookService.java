package com.takaichi00.sample.quarkus.application.mockcontroller;

import com.takaichi00.sample.quarkus.domain.Book;
import com.takaichi00.sample.quarkus.application.domain.BookService;
import io.quarkus.test.Mock;

import java.util.List;

@Mock
public class MockBookService implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return null;
  }

}
