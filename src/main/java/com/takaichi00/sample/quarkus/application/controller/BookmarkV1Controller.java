package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.common.interceptor.DumpLog;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1/bookmarks")
public class BookmarkV1Controller {

  @Inject
  BookService bookService;

  @DumpLog
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

  @DumpLog
  @POST
  @Path("/{isbn}")
  public Response registerBookmark(@PathParam String isbn, @Context UriInfo uriInfo) {
    bookService.registerBook(Isbn.of(isbn));
    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
    return Response.created(uriBuilder.build()).build();
  }

  @DumpLog
  @DELETE
  @Path("/{isbn}")
  public Response deleteBookmark(@PathParam String isbn) {
    bookService.deleteBookmark(Isbn.of(isbn));
    return Response.noContent().build();
  }

}
