package com.takaichi00.sample.quarkus.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

  @InjectMocks
  BookServiceImpl target;

  @Mock
  BookRepository mockBookRepository;

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

    // execute
    List<Book> actual = target.getAllBooks();

    // assert
    assertEquals(expected, actual);
  }

}