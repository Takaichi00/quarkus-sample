package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiMicroProfileClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.integration.dto.GoogleBooks;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;
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
    } catch (TimeoutException e) {
      throw new ApplicationException(Error.GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE.getqErrorMessage(null), e, Error.GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE);
    }

    if (result.getTotalItems() == 0) {
      throw new ApplicationException(Error.ISBN_NOTFOUND.getErrorMessage(isbn.toString()), Error.ISBN_NOTFOUND);
    }
    return Book.builder()
               .isbn(isbn)
               .title(result.getItems().get(0).volumeInfo.title)
               .authors(result.getItems().get(0).volumeInfo.authors)
               .url(BookUrl.of(result.getItems().get(0).volumeInfo.previewLink))
               .build();
  }
}
