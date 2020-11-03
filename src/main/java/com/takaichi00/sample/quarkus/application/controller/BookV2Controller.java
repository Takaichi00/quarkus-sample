package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.domain.BookMicroProfileService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import java.util.Arrays;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v2/books")
public class BookV2Controller {

  @Inject
  BookMicroProfileService bookMicroProfileService;

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookPayload searchBook(@PathParam String isbn) {

    Book result = bookMicroProfileService.searchBook(Isbn.of(isbn));

    return BookPayload.builder()
                      .isbn("1234567890123")
                      .title(result.getTitle())
                      .authors(Arrays.asList("1", "2"))
                      .url("http://example.com")
                      .build();
  }
}
