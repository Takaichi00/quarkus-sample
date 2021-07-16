package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.application.payload.ErrorPayload;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BookV1ControllerTest {
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
    // execute & assert
    given()
      .when()
        .pathParam("isbn", "invalid-isbn")
        .get("/v1/books/{isbn}")
      .then()
        .statusCode(400)
        .body("error_code", equalTo("0001"))
        .body("message", equalTo("isbn invalid-isbn is invalid"));
  }
}
