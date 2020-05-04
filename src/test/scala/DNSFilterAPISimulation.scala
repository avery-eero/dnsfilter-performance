import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class DNSFilterAPISimulation extends Simulation {

  val baseURL = "http://example.com"
  override def httpConf = super.httpConf.header("MyHeader", "MyValue")

  spec {
    http("Example index.html test")
      .get("/index.html")
      .check(h1.exists)
  }

}

object GatlingFunSpecExampleIT {

  def h1 = css("h1")

}