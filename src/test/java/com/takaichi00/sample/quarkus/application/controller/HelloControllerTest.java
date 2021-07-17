package com.takaichi00.sample.quarkus.application.controller;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class HelloControllerTest {
  @Test
  void test_v2_400Error_Controller() {
    // execute & assert
    given()
        .when()
        .get("/v1/hello")
        .then()
        .statusCode(200);
  }
}
