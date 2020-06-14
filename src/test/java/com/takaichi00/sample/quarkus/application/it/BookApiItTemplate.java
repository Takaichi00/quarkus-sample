package com.takaichi00.sample.quarkus.application.it;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookApiItTemplate {

  @Test
  void test_v1booksApi() {
    // setup
    List<BookPayload> expected = Arrays.asList(
            BookPayload.builder()
                    .isbn("test-isbn")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );


    // execute
    List<BookPayload> actual = given()
            .when()
            .get("/v1/books")
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getList(".", BookPayload.class);

    // assert
    assertThat(actual, is(expected));
  }
}
