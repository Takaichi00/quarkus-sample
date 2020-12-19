package com.takaichi00.sample.quarkus.application.exceptionhandler;

import static com.takaichi00.sample.quarkus.common.constant.Error.UNEXPECTED_ERROR;

import com.takaichi00.sample.quarkus.application.payload.ErrorPayload;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SystemExceptionHandler implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(RuntimeException e) {

    ErrorPayload errorPayload = ErrorPayload.builder()
                                            .errorCode(UNEXPECTED_ERROR.getErrorCode())
                                            .message(UNEXPECTED_ERROR.getErrorMessage(null))
                                            .build();

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                   .entity(errorPayload)
                   .build();
  }
}
