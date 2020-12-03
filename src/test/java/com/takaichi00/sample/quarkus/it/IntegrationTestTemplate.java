package com.takaichi00.sample.quarkus.it;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class IntegrationTestTemplate {

  public static final Operation DELETE_ALL = deleteAllFrom("books");
  public static final Operation INSERT_BOOKS = insertInto("books").columns("id", "isbn")
                                                                        .values(1, "9784043636037")
                                                                        .build();
  protected static WireMockServer wireMock = new WireMockServer(options().port(18080));

  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = System.getProperty("it.quarkus.url", "http://localhost");
    RestAssured.port = Integer.getInteger("it.quarkus.port", 8080);
    RestAssured.basePath = System.getProperty("it.quarkus.base.path", "");

    wireMock.start();
    WireMock.configureFor(18080);

  }

  @BeforeEach
  void beforeEach() {
    Operation operation = sequenceOf(DELETE_ALL, INSERT_BOOKS);
    DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:mysql://127.0.0.1:3306/test_database", "docker", "docker"), operation);
    dbSetup.launch();
  }

  @AfterAll
  static void tearDown() {
    wireMock.stop();
  }

}
