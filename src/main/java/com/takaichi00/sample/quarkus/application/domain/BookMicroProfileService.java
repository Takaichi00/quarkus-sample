package com.takaichi00.sample.quarkus.application.domain;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;

public interface BookMicroProfileService {
  Book searchBook(Isbn isbn);
}
