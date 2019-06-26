package org.acme.quickstart.infrastructure;

import javax.enterprise.context.ApplicationScoped;

import org.acme.quickstart.domain.client.BookRestClient;
import org.acme.quickstart.domain.model.Book;

@ApplicationScoped
public class BookRestClientImpl implements BookRestClient {

  @Override
  public Book getAllBooks() {
    // TODO Auto-generated method stub
    return new Book();
  }

}
