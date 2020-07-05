package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
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
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

@DefaultBean
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GoogleBooksApiClientImpl implements GoogleBooksApiClient {

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

    Response response = client.target("http://localhost:18080")
            .path("/books/v1/volumes")
            .queryParam("q", "isbn:9784043636037")
            .request()
            .get();


    return Arrays.asList(
            Book.builder()
                    .isbn("9784043636037")
                    .title("アラビアの夜の種族")
                    .authors(Arrays.asList("古川日出男"))
                    .build()
    );
  }

}
