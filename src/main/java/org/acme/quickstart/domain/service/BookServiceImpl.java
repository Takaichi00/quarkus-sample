package org.acme.quickstart.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.quickstart.domain.model.Book;

@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return new ArrayList<Book>();
  }

}
