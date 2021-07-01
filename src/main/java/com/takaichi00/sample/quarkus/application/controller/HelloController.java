package com.takaichi00.sample.quarkus.application.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Path("/v1/hello")
@Slf4j
public class HelloController {
  @GET
  public String getAllBookmarksIsbn() {
    log.info("hello!");
    return "hello";
  }
}
