package com.takaichi00.sample.quarkus.application.exceptionhandler;

import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {
  @Override
  public Response toResponse(ApplicationException e) {
    return null;
  }
}
