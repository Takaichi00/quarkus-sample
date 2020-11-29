package com.takaichi00.sample.quarkus.it;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BookApiItTemplate extends IntegrationTestTemplate {


  @Test
  void test_v1BooksApi() {
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
  }
}
