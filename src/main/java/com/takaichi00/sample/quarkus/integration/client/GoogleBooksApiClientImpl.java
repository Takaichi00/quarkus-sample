package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.arc.DefaultBean;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import lombok.RequiredArgsConstructor;

@DefaultBean
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GoogleBooksApiClientImpl implements GoogleBooksApiClient {

  private Client client;

  @Override
  public List<Book> getAllBooks(List<Isbn> isbnList) {
    return Arrays.asList(
            Book.builder()
                    .isbn("9784043636037")
                    .title("アラビアの夜の種族")
                    .authors(Arrays.asList("古川日出男"))
                    .build()
    );
  }

}
