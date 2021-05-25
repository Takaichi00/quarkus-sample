package com.takaichi00.sample.quarkus.application.mockcontroller;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.Mock;
import java.util.Arrays;
import java.util.List;

@Mock
public class MockBookService implements BookService {

  @Override
  public List<Isbn> getAllBookmarksIsbn() {
    return Arrays.asList(
        Isbn.of(1234567890123L));
  }

  @Override
  public List<Book> getAllBookmarks() {
    return Arrays.asList(
            Book.builder()
                    .isbn(Isbn.of(1234567890123L))
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .url(BookUrl.of("http://example.com/1"))
                    .build()
    );
  }

  @Override
  public Book searchBook(Isbn isbn) {
    return Book.builder()
               .isbn(Isbn.of(1234567890123L))
               .title("test-title")
               .authors(Arrays.asList("authors1", "authors2"))
               .url(BookUrl.of("http://example.com/1"))
               .build();
  }

  @Override
  public void registerBook(Isbn isbn) {

  }

  @Override
  public void deleteBookmark(Isbn of) {

  }

}
