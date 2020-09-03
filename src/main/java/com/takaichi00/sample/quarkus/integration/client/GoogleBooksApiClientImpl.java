package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.integration.dto.GoogleReadApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GoogleBooksApiClientImpl implements GoogleBooksApiClient {

  @ConfigProperty(name = "google.book.api.endpoint")
  protected String apiEndpoint;

  private Client client;

  @PostConstruct
  public void init() {
    ResteasyClientBuilder builder = (ResteasyClientBuilder) ClientBuilder.newBuilder();
    builder.connectionPoolSize(50);
    builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
    builder.readTimeout(10000, TimeUnit.MILLISECONDS);
    builder.connectionTTL(-1, TimeUnit.MILLISECONDS);
    client = builder.build();
  }

  @Override
  public List<Book> getAllBooks(List<Isbn> isbnList) {
    
    List<Book> books = new ArrayList<>();

    for (Isbn isbn : isbnList) {
      Response response = client.target(apiEndpoint)
                                .path("/books/v1/volumes")
                                .queryParam("q", "isbn:" + isbn)
                                .request()
                                .get();

      GoogleReadApiResponse googleReadApiResponse = response.readEntity(GoogleReadApiResponse.class);

      books.add(
              Book.builder()
                  .isbn(isbn)
                  .title(googleReadApiResponse.getItems().get(0).getVolumeInfo().getTitle())
                  .authors(googleReadApiResponse.getItems().get(0).getVolumeInfo().getAuthors())
                  .url(BookUrl.of(googleReadApiResponse.getItems().get(0).getVolumeInfo().getPreviewLink()))
                  .build()
      );
    }

    return books;
  }

  @Override
  public Book getBook(Isbn isbn) {

    Response response = client.target(apiEndpoint)
                              .path("/books/v1/volumes")
                              .queryParam("q", "isbn:" + isbn)
                              .request()
                              .get();

    GoogleReadApiResponse googleReadApiResponse = response.readEntity(GoogleReadApiResponse.class);

    if (googleReadApiResponse.getTotalItems() == 0) {
      throw new ApplicationException("isbn:" + isbn.toString() + " is not founds", "0003");
    }

    return Book.builder()
               .isbn(isbn)
               .title(googleReadApiResponse.getItems().get(0).getVolumeInfo().getTitle())
               .authors(googleReadApiResponse.getItems().get(0).getVolumeInfo().getAuthors())
               .url(BookUrl.of(googleReadApiResponse.getItems().get(0).getVolumeInfo().getPreviewLink()))
               .build();
  }
}
