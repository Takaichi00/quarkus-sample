package org.acme.quickstart.domain.service;

import java.util.List;

import org.acme.quickstart.domain.model.Book;

public interface BookService {
  
  public List<Book> getAllBooks();
  
}
