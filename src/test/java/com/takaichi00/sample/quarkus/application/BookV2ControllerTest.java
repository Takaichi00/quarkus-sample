package com.takaichi00.sample.quarkus.application;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.takaichi00.sample.quarkus.application.domain.BookMicroProfileService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.BookUrl;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class BookV2ControllerTest {

  @InjectMock
  BookMicroProfileService bookMicroProfileService;

  @Test
  void test_v2Controller() {
    Book book = Book.builder()
                    .title("title1")
                    .authors(Arrays.asList("author1", "author2"))
                    .isbn(Isbn.of("1234567890123"))
                    .url(BookUrl.of("https://example.com"))
                    .build();

    Mockito.when(bookMicroProfileService.searchBook(Isbn.of("1234567890123"))).thenReturn(book);

    BookPayload actual = given()
                          .when()
                            .pathParam("isbn", "1234567890123")
                            .get("/v2/books/{isbn}")
                          .then()
                            .statusCode(200)
                            .extract()
                            .body().jsonPath().getObject(".", BookPayload.class);
    assertEquals("title1", actual.getTitle());
  }

  @Test
  void test_v2Error() {
    given()
      .when()
        .pathParam("isbn", "invalidIsbn")
        .get("/v2/books/{isbn}")
      .then()
        .statusCode(400);
  }
}
