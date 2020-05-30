package com.takaichi00.sample.quarkus.application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/books")
public class BookController {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getAllBooks() {
    return "books";
  }
}
