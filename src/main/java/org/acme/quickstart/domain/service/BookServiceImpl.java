package org.acme.quickstart.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.quickstart.domain.client.BookRestClient;
import org.acme.quickstart.domain.model.Book;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Inject
  @RestClient
  BookRestClient bookRestClient;
  
  @Override
  public List<Book> getAllBooks() {
    String book = bookRestClient.getAllBooks();
    System.out.println(book);
    return new ArrayList<Book>();
  }

}
