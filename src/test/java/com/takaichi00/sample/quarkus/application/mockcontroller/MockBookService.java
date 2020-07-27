package com.takaichi00.sample.quarkus.application.mockcontroller;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.Mock;

import java.util.Arrays;
import java.util.List;

@Mock
public class MockBookService implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return Arrays.asList(
            Book.builder()
                    .isbn(Isbn.of(1234567890123L))
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .build()
    );
  }

}
