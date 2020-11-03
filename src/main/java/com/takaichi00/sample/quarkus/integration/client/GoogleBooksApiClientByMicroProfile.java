package com.takaichi00.sample.quarkus.integration.client;

import com.takaichi00.sample.quarkus.integration.dto.GoogleBooks;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/books")
@RegisterRestClient
public interface GoogleBooksApiClientByMicroProfile {

  @GET
  @Path("/v1/volumes")
  @Produces("application/json")
  GoogleBooks getByIsbn(@QueryParam("q") String q);
}
