package com.takaichi00.sample.quarkus.integration.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GoogleBooksApiClientImplTest {

  private static WireMockServer wireMock = new WireMockServer(options().port(18080));

  @Inject
  GoogleBooksApiClientImpl testTarget;

  @BeforeAll
  static void setupVariables() {
    wireMock.start();
    WireMock.configureFor(18080);
  }

  @AfterAll
  static void tearDown() {
    wireMock.stop();
  }

  @Test
  void test_getAllBooks() throws Exception {

    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
            .willReturn(aResponse().withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readMockResponseFile("isbnResponse.json"))));

    List<Isbn> requestIsbnList = Arrays.asList(
            Isbn.of(9784043636037L)
    );

    List<Book> expected = Arrays.asList(
      Book.builder()
        .isbn(Isbn.of(9784043636037L))
        .title("アラビアの夜の種族")
        .authors(Arrays.asList("古川日出男"))
        .url(BookUrl.of("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api"))
        .build()
    );


    // execute
    List<Book> actual = testTarget.getAllBooks(requestIsbnList);

    // assert
    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));
  }

  @Test
  void test_getBook() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    // execute
    Book actual = testTarget.getBook(Isbn.of(9784043636037L));

    // assert
    Book expected = Book.builder()
                        .isbn(Isbn.of(9784043636037L))
                        .title("アラビアの夜の種族")
                        .authors(Arrays.asList("古川日出男"))
                        .url(BookUrl.of("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api"))
                        .build();

    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));
  }

  private static String readMockResponseFile(String filename) throws IOException {
    try (FileInputStream input = new FileInputStream("src/test/resources/__files/" + filename)) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    }
  }

}
