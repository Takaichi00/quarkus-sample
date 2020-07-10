package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@DefaultBean
@ApplicationScoped
public class TmpGoogleBooksApiClient implements GoogleBooksApiClient {

  @Override
  public List<Book> getAllBooks(List<Isbn> isbnList) {
    return Arrays.asList(
            Book.builder()
                    .isbn("1234567890123")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );
  }

}
