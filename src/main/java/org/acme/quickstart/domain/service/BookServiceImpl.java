package org.acme.quickstart.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.quickstart.domain.client.HttpsSampleClient;
import org.acme.quickstart.domain.model.Book;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Inject
  @RestClient
  HttpsSampleClient httpsSampleClient;
  
  @Override
  public List<Book> getAllBooks() {
    String book = httpsSampleClient.getAllGithubUrl();
    System.out.println(book);
    return new ArrayList<Book>();
  }

}
