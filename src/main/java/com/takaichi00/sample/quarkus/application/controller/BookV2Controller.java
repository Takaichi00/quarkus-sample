package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import com.takaichi00.sample.quarkus.application.payload.BookPayload;
import com.takaichi00.sample.quarkus.domain.client.GoogleBooksApiClientByMicroProfile;
import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/v2/books")
public class BookV2Controller {

  @Inject
  @RestClient
  GoogleBooksApiClientByMicroProfile googleBooksApiClientByMicroProfile;

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookPayload searchBook(@PathParam String isbn) {
    googleBooksApiClientByMicroProfile.getByIsbn("isbn:" + "9784865942248");

    return BookPayload.builder()
                      .isbn("1234567890123")
                      .title("tmp")
                      .authors(Arrays.asList("1", "2"))
                      .url("http://example.com")
                      .build();
//    return BookPayload.builder()
//                      .isbn(book.getIsbn().toString())
//                      .title(book.getTitle())
//                      .authors(book.getAuthors())
//                      .url(book.getUrl().toString())
//                      .build();

  }
}
