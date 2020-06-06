package com.takaichi00.sample.quarkus.application;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BookControllerTest {

  @Test
  void test_v1books() {
    List<BookPayload> actual = given()
      .when()
        .get("/v1/books")
      .then()
        .statusCode(200)
        .extract()
        .body().jsonPath().getList(".", BookPayload.class);

    assertThat(actual.get(0).getIsbn(), is("test-isbn"));
  }
}