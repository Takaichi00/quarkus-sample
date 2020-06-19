package com.takaichi00.sample.quarkus.integration;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.takaichi00.sample.quarkus.domain.Book;
import com.takaichi00.sample.quarkus.domain.BookRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class BookRepositoryImplTest {

  public static final Operation DELETE_ALL = deleteAllFrom("books");
  public static final Operation INSERT_BOOKS = insertInto("books").columns("id").values(1).build();

  @Inject
  BookRepository bookRepository;

  @BeforeEach
  void setUp() {
    Operation operation = sequenceOf(DELETE_ALL, INSERT_BOOKS);
    DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1", "testuser", "testpass"), operation);
    dbSetup.launch();
  }

  @Test
  void getAllBooksTest() {
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
    List<Book> actual = bookRepository.getAllBooks();

    // assert
    assertEquals(expected, actual);
  }
}