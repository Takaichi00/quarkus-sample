package com.takaichi00.sample.quarkus.application;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.application.payload.ErrorPayload;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class BookmarkV1ControllerTest {

  @Test
  void test_getAllBooks() {
    // setup
    List<BookPayload> expected = Arrays.asList(
            BookPayload.builder()
                       .isbn("1234567890123")
                       .title("test-title")
                       .authors(Arrays.asList("authors1", "authors2"))
                       .url("http://example.com/1")
                       .build()
    );

    // execute
    List<BookPayload> actual = given()
                                 .when()
                                   .get("/v1/bookmarks")
                                 .then()
                                   .statusCode(200)
                                   .extract()
                                   .body().jsonPath().getList(".", BookPayload.class);

    // assert
    assertEquals(expected, actual);
  }

  @Test
  void test_searchBook() {
    // setup
    BookPayload expected = BookPayload.builder()
                                      .isbn("1234567890123")
                                      .title("test-title")
                                      .authors(Arrays.asList("authors1", "authors2"))
                                      .url("http://example.com/1")
                                      .build();

    // execute
    BookPayload actual = given()
                           .when()
                             .pathParam("isbn", "1234567890123")
                             .get("/v1/books/{isbn}")
                           .then()
                             .statusCode(200)
                             .extract()
                             .body().jsonPath().getObject(".", BookPayload.class);
    // assert
    assertEquals(expected, actual);
  }

  @Test
  void test_errorInvalidIsbn() {
    // setup
    ErrorPayload expected = ErrorPayload.builder()
      .errorCode("0001")
      .message("isbn is invalid:invalid-isbn")
      .build();

    // execute
    ErrorPayload actual = given()
                                .when()
                                  .pathParam("isbn", "invalid-isbn")
                                  .get("/v1/books/{isbn}")
                                .then()
                                  .statusCode(400)
                                  .extract()
                                  .body().jsonPath().getObject(".", ErrorPayload.class);
    // assert
    assertEquals(expected, actual);
  }

  @Test
  void test_registerBookmark() {

    // execute & assert
    given()
      .when()
        .pathParam("isbn", "1234567890123")
        .post("/v1/bookmarks/{isbn}")
      .then()
        .statusCode(201);
//        .header("Location:", "http://localhost:8080/v1/bookmarks/1234567890123");
  }

}
