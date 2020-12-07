package com.takaichi00.sample.quarkus.integration.repository;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookmarkRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class BookmarkRepositoryImplTest {

  public static final Operation DELETE_ALL = deleteAllFrom("books");
  public static final Operation INSERT_BOOKS = insertInto("books")
                                               .columns("id", "isbn")
                                               .values(1, "1234567890123")
                                               .build();

  @Inject
  BookmarkRepository bookmarkRepository;

  @BeforeEach
  void setUp() {
    Operation operation = sequenceOf(DELETE_ALL, INSERT_BOOKS);
    DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:test", "testuser", "testpass"), operation);
    dbSetup.launch();
  }

  @Test
  void getAllIsbnTest() {

    // setup
    List<Isbn> expected = Arrays.asList(
            Isbn.of(1234567890123L)
    );

    // execute
    List<Isbn> actual = bookmarkRepository.getAllIsbn();

    // assert
    assertEquals(expected, actual);

  }

  @Test
  void registerIsbn() {
    // setup
    Isbn expected = Isbn.of("1234567890124");
    
    // execute
    Isbn actual = bookmarkRepository.registerBookmark(Isbn.of("1234567890124"));

    // assert
    assertEquals(expected, actual);
  }

  @Test
  void registerDuplicatedIsbnError() {
    // setup
    Isbn expected = Isbn.of("1234567890124");

    // execute
    Isbn actual1 = bookmarkRepository.registerBookmark(Isbn.of("1234567890124"));
    ApplicationException actual2 = assertThrows(ApplicationException.class, () -> bookmarkRepository.registerBookmark(Isbn.of("1234567890124")));

    // assert
    assertEquals(expected, actual1);
    assertEquals(Error.REGISTER_BOOKMARK_IS_FAILED, actual2.getError());
    assertEquals("register bookmark is failed.", actual2.getMessage());
  }

  @Test
  void deleteIsbn() {
    // setup
    List<Isbn> expected = new ArrayList<>();

    // execute
    bookmarkRepository.deleteBookmark(Isbn.of("1234567890123"));

    // assert
    List<Isbn> actual = bookmarkRepository.getAllIsbn();
    assertEquals(expected, actual);
  }

}
