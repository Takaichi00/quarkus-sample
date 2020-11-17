package com.takaichi00.sample.quarkus.application.exceptionhandler;

import com.takaichi00.sample.quarkus.application.payload.ErrorPayload;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.common.interceptor.DumpLog;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {

  @Override
  public Response toResponse(ApplicationException e) {

    ErrorPayload errorPayload = ErrorPayload.builder()
                                            .errorCode(e.getErrorCode().getCode())
                                            .message(e.getMessage())
                                            .build();

    return Response.status(Response.Status.BAD_REQUEST)
                   .entity(errorPayload)
                   .build();
  }
}
