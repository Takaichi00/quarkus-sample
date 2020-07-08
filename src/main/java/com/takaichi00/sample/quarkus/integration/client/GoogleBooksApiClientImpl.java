package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.integration.dto.GoogleReadApiResponse;
import io.quarkus.arc.DefaultBean;
import java.util.Arrays;
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

@DefaultBean
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

    Response response = client.target(apiEndpoint)
            .path("/books/v1/volumes")
            .queryParam("q", "isbn:9784043636037")
            .request()
            .get();

    GoogleReadApiResponse result = response.readEntity(GoogleReadApiResponse.class);

    return Arrays.asList(
            Book.builder()
                    .isbn("9784043636037")
                    .title(result.getItems().get(0).getVolumeInfo().getTitle())
                    .authors(result.getItems().get(0).getVolumeInfo().getAuthors())
                    .build()
    );
  }

}
