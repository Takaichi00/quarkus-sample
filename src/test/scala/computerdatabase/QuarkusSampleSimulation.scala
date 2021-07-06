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
  setUp(scn.inject(
    // https://gatling.io/docs/gatling/reference/current/general/simulation_setup/
//        atOnceUsers(1), // 1回で x スレッド立ち上げてリクエストを実施
//        rampUsers(10).during(1.seconds), // x 秒かけて y スレッド立ち上げてリクエストを実施
//        heavisideUsers(100).during(10.seconds) // https://en.wikipedia.org/wiki/Heaviside_step_function#/media/File:Step_function_approximation.png のグラフのように、中間の時間まで徐々に単位時間あたりのリクエストが増加し、徐々に単位時間あたりのリクエストが減少する
//        constantUsersPerSec(2).during(10), // 1秒ごとに x スレッド、y 秒間実施 (open model)
//        constantConcurrentUsers(2).during(10.seconds) // 1秒ごとに x スレッド、y 秒間実施 (closed model)
    // Open model と Closed model の違い: https://gatling.io/2020/04/how-easily-can-i-perform-a-load-test/ , https://gatling.io/docs/gatling/reference/current/general/simulation_setup/#open-vs-closed-workload-models
      )
    ).assertions(
      global.responseTime.max.lt(200), // https://gatling.io/docs/gatling/reference/current/general/assertions/
      global.successfulRequests.percent.gt(95)
    ).protocols(httpProtocol)
}