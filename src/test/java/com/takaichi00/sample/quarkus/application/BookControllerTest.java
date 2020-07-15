package com.takaichi00.sample.quarkus.application;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class BookControllerTest {

  @Test
  void test_v1books() {
    // setup
    List<BookPayload> expected = Arrays.asList(
            BookPayload.builder()
                    .isbn("1234567890123")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .queryParam("?isbn=1234567890123&title=test-title&author=authors1&author=authors2")
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
    assertEquals(expected, actual);
  }
}
