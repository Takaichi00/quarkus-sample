package org.acme.quickstart.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.acme.quickstart.application.payload.BookPayload;
import org.acme.quickstart.application.payload.BooksPayload;
import org.acme.quickstart.domain.model.Book;
import org.acme.quickstart.domain.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("/book エンドポイントのテスト")
class BookControllerTest {

  @InjectMocks
  BookController testTarget;
  
  @Mock
  BookService bookService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  void bookエンドポイントはすべての本を取得できる() {
    // setup
    List<BookPayload> expectedBooksPayload = new ArrayList<BookPayload>() {{
        this.add(BookPayload.builder().title("title1").price(1000).build());
    }};
    BooksPayload expected = BooksPayload.builder().bookPayload(expectedBooksPayload).build();
    
    List<Book> mockBooks = new ArrayList<Book>() {{
      this.add(new Book("title1", 1000));
    }};
    when(bookService.getAllBooks()).thenReturn(mockBooks);
    
    // execute
    BooksPayload actual = testTarget.getAllBooks();
    
    // assertion
    assertEquals(actual, expected);
    verify(bookService).getAllBooks();
  }
  
  @Test
  void bookエンドポイントは本がなかった場合は0冊と返す() {
    // setup
    List<BookPayload> expectedBooksPayload = new ArrayList<BookPayload>() {{}};
    BooksPayload expected = BooksPayload.builder().bookPayload(expectedBooksPayload).build();
    
    when(bookService.getAllBooks()).thenReturn(new ArrayList<Book>());
    
    // execute
    BooksPayload actual = testTarget.getAllBooks();
    
    // assertion
    assertEquals(actual, expected);
    verify(bookService).getAllBooks();
  }
}
