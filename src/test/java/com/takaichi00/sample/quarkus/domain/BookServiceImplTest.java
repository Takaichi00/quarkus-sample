package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClient;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookRepository;
import com.takaichi00.sample.quarkus.domain.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

  @InjectMocks
  BookServiceImpl target;

  @Mock
  BookRepository mockBookRepository;

  @Mock
  GoogleBooksApiClient googleBooksApiClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void test_getAllBooks() {

    // setup
    List<Book> expected = Arrays.asList(
            Book.builder()
                    .isbn("test-isbn")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );

    List<Isbn> mockReturnIsbnList = Arrays.asList(
            Isbn.of(1234567890123L)
    );

    List<Book> mockReturnBooks = Arrays.asList(
            Book.builder()
                    .isbn("test-isbn")
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );

    when(mockBookRepository.getAllIsbn()).thenReturn(mockReturnIsbnList);
    when(googleBooksApiClient.getAllBooks(mockReturnIsbnList)).thenReturn(mockReturnBooks);

    // execute
    List<Book> actual = target.getAllBooks();

    // assert
    assertEquals(expected, actual);
    verify(mockBookRepository, times(1)).getAllIsbn();
    verify(googleBooksApiClient, times(1)).getAllBooks(mockReturnIsbnList);
  }

}