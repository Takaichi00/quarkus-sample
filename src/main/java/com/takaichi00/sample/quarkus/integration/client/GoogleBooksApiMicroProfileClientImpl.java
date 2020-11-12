package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.common.constant.ErrorCode;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiMicroProfileClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.integration.dto.GoogleBooks;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GoogleBooksApiMicroProfileClientImpl implements GoogleBooksApiMicroProfileClient {

  @Inject
  @RestClient
  GoogleBooksApiClientByMicroProfile googleBooksApiClientByMicroProfile;

  @Override
  public Book getBook(Isbn isbn) {
    GoogleBooks result;
    try {
      result = googleBooksApiClientByMicroProfile.getByIsbn("isbn:" + isbn);
    } catch (ProcessingException e) {
      throw new ApplicationException("Google Books API Request Failed (MicroProfile).", e, ErrorCode.GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE);
    }

    if (result.getTotalItems() == 0) {
      throw new ApplicationException("isbn:" + isbn.toString() + " is not founds", ErrorCode.ISBN_NOTFOUND);
    }
    return Book.builder()
               .isbn(isbn)
               .title(result.getItems().get(0).volumeInfo.title)
               .authors(result.getItems().get(0).volumeInfo.authors)
               .url(BookUrl.of(result.getItems().get(0).volumeInfo.previewLink))
               .build();
  }
}
