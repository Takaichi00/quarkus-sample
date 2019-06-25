package org.acme.quickstart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;


class GreetingServiceTest {

  @Test
  void test() {
    GreetingService testTarget = new GreetingService();
    String actual = testTarget.greeting("test");
    assertEquals(actual,"hello test\n");
  }

}
