package com.takaichi00.sample.quarkus.integration.repository;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookRepository;
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
  public static final Operation INSERT_BOOKS = insertInto("books")
                                               .columns("id", "isbn")
                                               .values(1, "1234567890123")
                                               .build();

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
                    .isbn("1234567890123")
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

  @Test
  void getAllIsbnTest() {

    // setup
    List<Isbn> expected = Arrays.asList(
            Isbn.of(1234567890123L)
    );

    // execute
    List<Isbn> actual = bookRepository.getAllIsbn();

    // assert
    assertEquals(expected, actual);

  }

}