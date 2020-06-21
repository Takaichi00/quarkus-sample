package com.takaichi00.sample.quarkus.application.mockcontroller;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.application.domain.BookService;
import io.quarkus.test.Mock;

import java.util.Arrays;
import java.util.List;

@Mock
public class MockBookService implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return Arrays.asList(
            Book.builder()
                    .isbn("test-isbn")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );
  }

}
