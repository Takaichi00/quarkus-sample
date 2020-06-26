package com.takaichi00.sample.quarkus.domain.service;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookRepository;
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
    List<Isbn> isbnList = bookRepository.getAllIsbn();

    // Do something

    return allBooks;
  }
}