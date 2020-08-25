package com.takaichi00.sample.quarkus.domain.service;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookmarkRepository;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.RequiredArgsConstructor;

// refer to: https://quarkus.io/blog/quarkus-dependency-injection/
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookServiceImpl implements BookService {

  private final BookmarkRepository bookmarkRepository;

  private final GoogleBooksApiClient googleBooksApiClient;

  @Override
  public List<Book> getAllBookmarks() {

    List<Isbn> isbnList = bookmarkRepository.getAllIsbn();

    List<Book> allBooks = googleBooksApiClient.getAllBooks(isbnList);

    // Do something

    return allBooks;
  }

  @Override
  public Book searchBook(Isbn isbn) {
    return googleBooksApiClient.getBook(isbn);
  }

  @Override
  public void registerBook(Isbn isbn) {

  }
}
