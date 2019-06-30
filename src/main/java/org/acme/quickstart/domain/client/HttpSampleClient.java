package org.acme.quickstart.domain.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1")
@RegisterRestClient
public interface HttpSampleClient {
  
  @GET
  @Path("employees")
  public String getAllEmployee();
}
