package com.takaichi00.sample.quarkus.domain;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

  @InjectMocks
  BookServiceImpl target;

  @Mock
  BookRepository mockBookRepository;

  @Test
  void test_getAllBooks() {

  }

}