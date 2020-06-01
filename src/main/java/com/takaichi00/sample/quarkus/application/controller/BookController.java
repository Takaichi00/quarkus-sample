package com.takaichi00.sample.quarkus.application.controller;

import com.takaichi00.sample.quarkus.application.payload.BookPayload;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/books")
public class BookController {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public BookPayload getAllBooks() {
    return BookPayload.builder().isbn("test-isbn").build();
  }
}
