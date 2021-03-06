package com.takaichi00.sample.quarkus.integration.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.takaichi00.sample.quarkus.TestUtils.readMockResponseFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

  @Test
  void test_getBookNotFound() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9781111111111"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponseTotal0.json"))));

    // execute
    ApplicationException actual = assertThrows(ApplicationException.class, () -> testTarget.getBook(Isbn.of(9781111111111L)));

    // assert
    assertEquals(Error.ISBN_NOTFOUND, actual.getError());
    assertEquals("isbn 9781111111111 is not found", actual.getMessage());

    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9781111111111")));
  }
}
