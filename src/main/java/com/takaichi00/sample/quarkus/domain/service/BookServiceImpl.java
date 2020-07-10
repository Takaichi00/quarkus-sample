package com.takaichi00.sample.quarkus.domain.service;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookRepository;
import io.quarkus.arc.DefaultBean;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.RequiredArgsConstructor;

// https://quarkus.io/blog/quarkus-dependency-injection/
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final GoogleBooksApiClient googleBooksApiClient;

  @Override
  public List<Book> getAllBooks() {

    List<Isbn> isbnList = bookRepository.getAllIsbn();

    List<Book> allBooks = googleBooksApiClient.getAllBooks(isbnList);

    // Do something

    return allBooks;
  }
}