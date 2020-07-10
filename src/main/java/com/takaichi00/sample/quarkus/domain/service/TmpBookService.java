package com.takaichi00.sample.quarkus.domain.service;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.model.Book;
import io.quarkus.arc.DefaultBean;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@DefaultBean
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
