package com.takaichi00.sample.quarkus.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookmarkRepository;
import com.takaichi00.sample.quarkus.domain.service.BookServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookServiceImplTest {

  @InjectMocks
  BookServiceImpl target;

  @Mock
  BookmarkRepository mockBookmarkRepository;

  @Mock
  GoogleBooksApiClient mockGoogleBooksApiClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void test_getAllBookmark() {

    // setup
    List<Book> expected = Arrays.asList(
            Book.builder()
                    .isbn(Isbn.of(1234567890123L))
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .build()
    );

    List<Isbn> mockReturnIsbnList = Arrays.asList(
            Isbn.of(1234567890123L)
    );

    List<Book> mockReturnBooks = Arrays.asList(
            Book.builder()
                    .isbn(Isbn.of(1234567890123L))
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .build()
    );

    when(mockBookmarkRepository.getAllIsbn()).thenReturn(mockReturnIsbnList);
    when(mockGoogleBooksApiClient.getAllBooks(mockReturnIsbnList)).thenReturn(mockReturnBooks);

    // execute
    List<Book> actual = target.getAllBookmarks();

    // assert
    assertEquals(expected, actual);
    verify(mockBookmarkRepository, times(1)).getAllIsbn();
    verify(mockGoogleBooksApiClient, times(1)).getAllBooks(mockReturnIsbnList);
  }

  @Test
  void test_searchBooks() {
    // setup
    Book expected = Book.builder()
            .isbn(Isbn.of(1234567890123L))
            .title("test-title")
            .authors(Arrays.asList("authors1", "authors2"))
            .build();

    Book mockReturn = Book.builder()
            .isbn(Isbn.of(1234567890123L))
            .title("test-title")
            .authors(Arrays.asList("authors1", "authors2"))
            .build();

    when(mockGoogleBooksApiClient.getBook(Isbn.of(1234567890123L))).thenReturn(mockReturn);

    // execute
    Book actual = target.searchBook(Isbn.of(1234567890123L));

    // assert
    assertEquals(expected, actual);
    verify(mockGoogleBooksApiClient, times(1)).getBook(Isbn.of(1234567890123L));
  }

  @Test
  void test_registerBookmark() {
    // execute
    target.registerBook(Isbn.of(1234567890123L));

    // assert
    verify(mockGoogleBooksApiClient, times(1)).getBook(Isbn.of(1234567890123L));
    verify(mockBookmarkRepository, times(1)).registerBookmark(Isbn.of(1234567890123L));
  }

  @Test
  void test_deleteBookmark() {
    // execute
    target.deleteBookmark(Isbn.of(1234567890123L));

    // setup
    verify(mockBookmarkRepository, times(1)).deleteBookmark(Isbn.of(1234567890123L));
  }
}
