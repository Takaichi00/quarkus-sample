package com.takaichi00.sample.quarkus.common.interceptor;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Priority(APPLICATION + 100)
@Interceptor
@DumpLog
public class DumpLogInterceptor {

  @AroundInvoke
  public Object dumpLog(InvocationContext context) throws Exception {

    if (!log.isDebugEnabled()) {
      return context.proceed();
    }

    StringBuilder requestString = new StringBuilder();

    for (Object parameter: context.getParameters()) {
      requestString.append(parameter);
    }

    log.debug("Method is invoked: " + context.getMethod());
    log.debug("Before request parameters:" + requestString.toString());

    Object proceeded = context.proceed();

    Object returnValueString;

    if (proceeded instanceof Response) {
      returnValueString = ((Response) proceeded).getEntity().toString();
    } else {
      returnValueString = proceeded.toString();
    }

    log.debug("After response parameters:" + returnValueString);
    return proceeded;
  }
}
