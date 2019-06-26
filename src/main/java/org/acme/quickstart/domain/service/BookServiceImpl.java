package org.acme.quickstart.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.quickstart.domain.client.BookRestClient;
import org.acme.quickstart.domain.model.Book;

@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Inject
  BookRestClient bookRestClient;
  
  @Override
  public List<Book> getAllBooks() {
    return new ArrayList<Book>();
  }

}
