package com.takaichi00.sample.quarkus.it;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookApiItTemplate {

  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "";
  }

  @Test
  void test_v1booksApi() {
    // setup
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
    assertThat(actual, is(expected));
  }
}
