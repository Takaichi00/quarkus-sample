package com.takaichi00.sample.quarkus.it;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BookmarkApiItTemplate extends IntegrationTestTemplate {

  @Test
  void test_v1GetBookmarksApi() {
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
    assertEquals(expected, actual);
  }

  @Test
  void test_v1RegisterBookmarkApi() {
    // execute
    given().when()
             .pathParam("isbn", "9784865942248")
             .post("/v1/bookmarks/{isbn}")
           .then()
             .statusCode(201);
  }

}
