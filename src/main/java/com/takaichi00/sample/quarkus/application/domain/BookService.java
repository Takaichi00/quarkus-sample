package com.takaichi00.sample.quarkus.application.domain;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import java.util.List;

public interface BookService {

  List<Isbn> getAllBookmarksIsbn();

  List<Book> getAllBookmarks();

  Book searchBook(Isbn isbn);

  void registerBook(Isbn isbn);

  void deleteBookmark(Isbn of);
}
