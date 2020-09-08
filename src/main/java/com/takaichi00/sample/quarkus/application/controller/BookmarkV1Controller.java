package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.domain.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.takaichi00.sample.quarkus.domain.model.Isbn;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1/bookmarks")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BookmarkV1Controller {

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

  @POST
  @Path("/{isbn}")
  public Response registerBookmark(@PathParam String isbn, @Context UriInfo uriInfo) {
    bookService.registerBook(Isbn.of(isbn));
    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
    return Response.created(uriBuilder.build()).build();
  }

}
