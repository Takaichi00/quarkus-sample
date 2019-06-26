package org.acme.quickstart.domain.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.acme.quickstart.domain.model.Book;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1")
@RegisterRestClient
public interface BookRestClient {

  @GET
  @Path("/employees")
  public String getAllBooks();
  
}
