package com.takaichi00.sample.quarkus.application;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BookControllerTest {

  @Test
  void test_v1books() {
    given()
      .when()
        .get("/v1/books")
      .then()
        .statusCode(200)
        .body("isbn", equalTo("test-isbn"));
  }
}