package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class QuarkusSampleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader("application/json") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")

  val scn = scenario("Sample") // A scenario is a chain of requests and pauses
    .exec(http("request_hello")
      .get("/v1/hello"))
    .pause(1) // Note that Gatling has recorder real time pauses

  // https://hkawabata.github.io/technical-note/note/OSS/gatling.html
  setUp(scn.inject(atOnceUsers(50))
    .throttle(
      reachRps(50) in (100 seconds) // x秒 の間 y rps を実行する
    )).assertions(
      global.responseTime.max.lt(50), // https://gatling.io/docs/gatling/reference/current/general/assertions/
      global.successfulRequests.percent.gt(95)
    ).protocols(httpProtocol)
}
