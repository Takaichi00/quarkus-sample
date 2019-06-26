package org.acme.quickstart.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

class BookRestClientImplTest {

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
//    verify(postRequestedFor(urlEqualTo("/book/test")));
//        .withHeader("Content-Type", equalTo("application/json")));
  }

}
