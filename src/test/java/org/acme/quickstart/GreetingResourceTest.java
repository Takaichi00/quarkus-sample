package org.acme.quickstart;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.DisabledOnSubstrate;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.UUID;

@QuarkusTest
public class GreetingResourceTest {

  @TestHTTPResource("/hello")
  URL testUrl;

  @Test
  public void testHelloEndpoint() {
    System.out.println(testUrl.getHost());
    System.out.println(testUrl.getPath());
    System.out.println(testUrl.getPort());
    
    given().when().get("/hello").then().statusCode(200).body(is("hello"));
  }

  @Test
  public void testGreetingEndpoint() {
    String uuid = UUID.randomUUID().toString();
    given().pathParam("name", uuid).when().get("/hello/greeting/{name}").then().statusCode(200)
        .body(is("hello " + uuid + "\n"));
  }
  
  @DisabledOnSubstrate
  @Test
  public void testHoge() {
    GreetingResource testTarget = new GreetingResource();
    String actual = testTarget.hoge();
    assertEquals(actual, "hoge");
  }

}