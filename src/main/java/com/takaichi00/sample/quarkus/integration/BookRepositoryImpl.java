package com.takaichi00.sample.quarkus.integration;

import com.takaichi00.sample.quarkus.domain.Book;
import com.takaichi00.sample.quarkus.domain.BookRepository;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {

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
