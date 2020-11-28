package com.takaichi00.sample.quarkus.it;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookmarkApiItTemplate {

  public static final Operation DELETE_ALL = deleteAllFrom("books");
  public static final Operation INSERT_BOOKS = insertInto("books").columns("id", "isbn")
                                                                        .values(1, "9784043636037")
                                                                        .build();


  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "";
  }

  @BeforeEach
  void beforeEach() {
    Operation operation = sequenceOf(DELETE_ALL, INSERT_BOOKS);
    DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:mysql://127.0.0.1:3306/test_database", "docker", "docker"), operation);
    dbSetup.launch();
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
    assertEquals(expected, actual);
  }
}
