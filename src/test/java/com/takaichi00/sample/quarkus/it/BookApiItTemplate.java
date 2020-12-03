package com.takaichi00.sample.quarkus.it;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.takaichi00.sample.quarkus.TestUtils.readMockResponseFile;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;

import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BookApiItTemplate extends IntegrationTestTemplate {


  @Test
  void test_v1GetBooksApi() throws Exception {

    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    // setup
    BookPayload expected = BookPayload.builder().isbn("9784043636037")
                                                .title("アラビアの夜の種族")
                                                .authors(Arrays.asList("古川日出男"))
                                                .url("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api")
                                                .build();

    // execute
    BookPayload actual = given().when()
                                  .pathParam("isbn", "9784043636037")
                                  .get("/v1/books/{isbn}")
                                .then()
                                  .statusCode(200)
                                .extract()
                                  .body().jsonPath().getObject(".", BookPayload.class);

    // assert
    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));
  }

  @Test
  void test_v2GetBooksApi() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    BookPayload expected = BookPayload.builder().isbn("9784043636037")
                                                .title("アラビアの夜の種族")
                                                .authors(Arrays.asList("古川日出男"))
                                                .url("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api")
                                                .build();

    // execute
    BookPayload actual = given().when()
                                  .pathParam("isbn", "9784043636037")
                                  .get("/v2/books/{isbn}")
                                .then()
                                  .statusCode(200)
                                .extract()
                                  .body().jsonPath().getObject(".", BookPayload.class);

    // assert
    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));
  }
}
