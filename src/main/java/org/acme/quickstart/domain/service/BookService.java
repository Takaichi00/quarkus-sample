package org.acme.quickstart.domain.service;

import java.util.List;

import javax.ws.rs.Path;

import org.acme.quickstart.domain.model.Book;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//@Path("/v2")
//@RegisterRestClient
public interface BookService {
  
  public List<Book> getAllBooks();
  
}
