package com.takaichi00.sample.quarkus.integration.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

class GoogleBooksApiClientImplTest {

  private static WireMockServer wireMock = new WireMockServer(options().port(18080));

  @BeforeAll
  static void setupVariables() {
    wireMock.start();
  }

  @AfterAll
  static void tearDown() {
    wireMock.stop();
  }

  @Test
  void test_isbnApi() throws IOException {
    wireMock.stubFor(post(urlEqualTo("/books/v1/volumes?q=isbn:9784043636037"))
            .willReturn(aResponse().withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readMockResponseFile("isbnResponse.json"))));
  }

  private static String readMockResponseFile(String filename) throws IOException {
    try (FileInputStream input = new FileInputStream("src/test/resources/__files/" + filename)) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    }
  }

}