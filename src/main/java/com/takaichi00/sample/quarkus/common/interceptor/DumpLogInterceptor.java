package com.takaichi00.sample.quarkus.common.interceptor;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Priority(APPLICATION + 100)
@Interceptor
@DumpLog
public class DumpLogInterceptor {

  @AroundInvoke
  public Object dumpLog(InvocationContext context) throws Exception {
    log.debug("before invoke success!");
    Object proceeded = context.proceed();
    log.debug("after invoke success!");
    return proceeded;
  }

}
