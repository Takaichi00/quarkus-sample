package com.takaichi00.sample.quarkus.mprestclient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/google/books")
public class GoogleBookResource {

  @Inject
  @RestClient
  GoogleBooksService googleBooksService;

  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public GoogleBooks name(@PathParam String isbn) {
    return googleBooksService.getByIsbn("isbn:9784043636037");
  }
}
