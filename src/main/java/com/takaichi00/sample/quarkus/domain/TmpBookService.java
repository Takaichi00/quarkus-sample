package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TmpBookService implements BookService {

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
