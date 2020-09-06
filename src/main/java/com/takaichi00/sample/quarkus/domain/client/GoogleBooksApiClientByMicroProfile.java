package com.takaichi00.sample.quarkus.domain.client;

import com.takaichi00.sample.quarkus.integration.dto.GoogleReadApiResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1")
@RegisterRestClient(configKey = "google-api")
public interface GoogleBooksApiClientByMicroProfile {

  @GET
  @Path("/books/v1/volumes/{q}")
  @Produces("application/json")
  GoogleReadApiResponse getByIsbn(@PathParam String q);
}
