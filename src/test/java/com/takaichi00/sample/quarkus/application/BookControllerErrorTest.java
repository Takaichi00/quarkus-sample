package com.takaichi00.sample.quarkus.application;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.model.Book;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.List;

import static io.restassured.RestAssured.given;

// see https://quarkus.io/guides/getting-started-testing

//@QuarkusTest
public class BookControllerErrorTest {

//  @InjectMock
  BookService mockBookService;

//  public static class MockBookService implements BookService {
//
//    @Override
//    public List<Book> getAllBooks() {
////      throw new ApplicationException("isbn is invalid:", "0001");
//      return null;
//    }
//  }

//  @Test
  void test_whenBookServiceThrowsErrorThenReturnErrorResponse() {
    // setup
    Mockito.when(mockBookService.getAllBooks()).thenReturn(null);

//    QuarkusMock.installMockForInstance(new MockBookService(), mockBookService);

    given()
      .when()
      .get("/v1/books")
      .then()
      .statusCode(400);

  }

}
