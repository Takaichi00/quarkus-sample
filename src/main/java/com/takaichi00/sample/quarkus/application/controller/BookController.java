package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.domain.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.takaichi00.sample.quarkus.domain.model.Isbn;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1/books")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookController {

  private final BookService bookService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<BookPayload> getAllBooks() {

    List<Book> allBooks = bookService.getAllBooks();

    List<BookPayload> bookPayloadList = new ArrayList<>();
    for (Book book : allBooks) {
      bookPayloadList.add(
              BookPayload.builder()
                         .isbn(book.getIsbn().toString())
                         .title(book.getTitle())
                         .authors(book.getAuthors())
                         .url(book.getUrl().toString())
                         .build()
      );
    }
    return bookPayloadList;
  }

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookPayload getBook(@PathParam String isbn) {

    Book book = bookService.getBook(Isbn.of(isbn));

    return BookPayload.builder()
                      .isbn(book.getIsbn().toString())
                      .title(book.getTitle())
                      .authors(book.getAuthors())
                      .url(book.getUrl().toString())
                      .build();

  }
}
