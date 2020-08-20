package com.takaichi00.sample.quarkus.application;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import org.mockito.Mockito;

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
    Mockito.when(mockBookService.getAllBookmarks()).thenReturn(null);

//    QuarkusMock.installMockForInstance(new MockBookService(), mockBookService);

    given()
      .when()
      .get("/v1/books")
      .then()
      .statusCode(400);

  }

}
