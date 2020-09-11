package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiMicroProfileClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GoogleBooksApiMicroProfileClientImpl implements GoogleBooksApiMicroProfileClient {

  @Inject
  @RestClient
  GoogleBooksApiClientByMicroProfile googleBooksApiClientByMicroProfile;

  @Override
  public Book getBook(Isbn isbn) {
    googleBooksApiClientByMicroProfile.getByIsbn("isbn:" + isbn);
    return null;
  }
}
