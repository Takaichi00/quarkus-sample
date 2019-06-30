package org.acme.quickstart.infrastructure;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.acme.quickstart.domain.client.HttpsSampleClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.junit.DisabledOnSubstrate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class HttpsSampleClientTest {

  @Inject
  @RestClient
  HttpsSampleClient httpsRestClient;
  
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

  @DisabledOnSubstrate
  @Test
  void test() {
//    wireMockServer.stubFor(get(urlEqualTo("/api/v1/employees"))
    wireMockServer.stubFor(get(urlEqualTo("/"))
        .willReturn(aResponse().withHeader("Content-Type", "text/json")
                .withStatus(200)
                .withBodyFile("getBook.json")));
    
    String actual = httpsRestClient.getAllGithubUrl();
    String expected = "{\"title\":\"testTitle\",\"price\":1000}";
    
    assertEquals(actual, expected);

    
//    verify(postRequestedFor(urlEqualTo("/book/test")));
//        .withHeader("Content-Type", equalTo("application/json")));
  }

}
