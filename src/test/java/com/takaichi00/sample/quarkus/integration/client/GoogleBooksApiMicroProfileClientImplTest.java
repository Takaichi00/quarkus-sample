package com.takaichi00.sample.quarkus.integration.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.takaichi00.sample.quarkus.common.constant.ErrorCode;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GoogleBooksApiMicroProfileClientImplTest {

  private static WireMockServer wireMock = new WireMockServer(options().port(18080));

  @Inject
  GoogleBooksApiMicroProfileClientImpl testTarget;

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
  void test_getBookByIsbn() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    Book expected = Book.builder()
                        .isbn(Isbn.of(9784043636037L))
                        .title("アラビアの夜の種族")
                        .authors(Arrays.asList("古川日出男"))
                        .url(BookUrl.of("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api"))
                        .build();

    Book actual = testTarget.getBook(Isbn.of("9784043636037"));

    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));

  }

  @Test
  void test_Timeout_getBookByIsbn() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse()
                    .withStatus(200)
                    .withFixedDelay(3500)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readMockResponseFile("isbnResponse.json"))));

    ApplicationException actual = assertThrows(ApplicationException.class, () -> testTarget.getBook(Isbn.of("9784043636037")));

    assertEquals(ErrorCode.GOOGLE_BOOKS_API_REQUEST_FAILED_BY_MICRO_PROFILE, actual.getErrorCode());
    assertEquals("Google Books API Request Failed (MicroProfile).", actual.getMessage());
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));

  }

  private static String readMockResponseFile(String filename) throws IOException {
    try (FileInputStream input = new FileInputStream("src/test/resources/__files/" + filename)) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    }
  }
}
