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
import lombok.RequiredArgsConstructor;

@Path("/v1/bookmarks")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookmarkController {

  private final BookService bookService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<BookPayload> getAllBookmarks() {

    List<Book> allBooks = bookService.getAllBookmarks();

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
}
