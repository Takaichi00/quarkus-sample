package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import io.quarkus.arc.DefaultBean;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.RequiredArgsConstructor;

// https://quarkus.io/blog/quarkus-dependency-injection/
@DefaultBean
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public List<Book> getAllBooks() {

    List<Book> allBooks = bookRepository.getAllBooks();

    // Do something

    return allBooks;
  }
}