package com.takaichi00.sample.quarkus.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BookTest {

  @Test
  void test_getQueryParamString() {
    // setup
    Book testTarget = Book.builder()
            .isbn(Isbn.of(1234567890123L))
            .title("TestTitle")
            .authors(Arrays.asList("A", "B"))
            .build();
    // execute
    String actual = testTarget.getQueryParamString();
    // assert
    String expected = "?isbn=1234567890123&title=TestTitle&author=A&author=B";
    assertEquals(expected, actual);
  }
}
