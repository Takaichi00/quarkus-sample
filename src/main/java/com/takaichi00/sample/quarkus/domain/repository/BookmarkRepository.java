package com.takaichi00.sample.quarkus.domain.repository;

import com.takaichi00.sample.quarkus.domain.model.Isbn;
import java.util.List;

public interface BookmarkRepository {
  List<Isbn> getAllIsbn();
  Isbn registerBookmark(Isbn isbn);
  void deleteBookmark(Isbn isbn);
}
