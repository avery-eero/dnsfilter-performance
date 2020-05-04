import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import scala.concurrent.duration._

object DNSFilterAPISimulation {

  val baseURL = "http://example.com"

  def healthCheck(interval: FiniteDuration) = forever(
    exec(
      http("Health Check")
        .get("/healthcheck")
        .check(status.is(200))
    ).pace(interval)
  )

}
