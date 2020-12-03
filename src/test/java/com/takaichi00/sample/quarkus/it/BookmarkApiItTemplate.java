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

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BookmarkApiItTemplate extends IntegrationTestTemplate {

  @Test
  void test_v1GetBookmarksApi() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    List<BookPayload> expected = Arrays.asList(
            BookPayload.builder()
                       .isbn("9784043636037")
                       .title("アラビアの夜の種族")
                       .authors(Arrays.asList("古川日出男"))
                       .url("http://books.google.co.jp/books?id=s4CRHAAACAAJ&dq=isbn:9784043636037&hl=&cd=1&source=gbs_api")
                       .build()
    );


    // execute
    List<BookPayload> actual = given().when()
                                        .get("/v1/bookmarks")
                                      .then()
                                        .statusCode(200)
                                      .extract()
                                        .body().jsonPath().getList(".", BookPayload.class);

    // assert
    assertEquals(expected, actual);
    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784043636037")));
  }

  @Test
  void test_v1RegisterBookmarkApi() throws Exception {
    // setup
    wireMock.stubFor(get(urlEqualTo("/books/v1/volumes?q=isbn%3A9784865942248"))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(readMockResponseFile("isbnResponse.json"))));

    // execute & assert
    given().when()
             .pathParam("isbn", "9784865942248")
             .post("/v1/bookmarks/{isbn}")
           .then()
             .statusCode(201);

    verify(getRequestedFor(urlEqualTo("/books/v1/volumes?q=isbn%3A9784865942248")));
  }

}
