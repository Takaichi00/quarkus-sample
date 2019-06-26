package org.acme.quickstart.infrastructure;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.acme.quickstart.domain.client.BookRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class BookRestClientImplTest {

  @Inject
  @RestClient
  BookRestClient bookRestClient;
  
  WireMockServer wireMockServer;
  
  @BeforeEach
  public void setup () {
      wireMockServer = new WireMockServer(28080);
      wireMockServer.start();
  }

  @AfterEach
  public void teardown () {
      wireMockServer.stop();
  }

  @Test
  void test() {
    wireMockServer.stubFor(get(urlEqualTo("/api/v1/employees"))
        .willReturn(aResponse().withHeader("Content-Type", "text/json")
                .withStatus(200)
                .withBodyFile("getBook.json")));
    
    String actual = bookRestClient.getAllBooks();
    String expected = "{\"title\":\"testTitle\",\"price\":1000}";
    
    assertEquals(actual, expected);

    
//    verify(postRequestedFor(urlEqualTo("/book/test")));
//        .withHeader("Content-Type", equalTo("application/json")));
  }

}
