import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.io._
object Search {
	val feeder = csv("D:/yeridtest.csv") //.random 
	println(feeder.toString)
	  val search = feed(feeder)
	.exec(http("search")
	.get("/")
	.queryParam("""itemID""", """192237701""")
	.queryParam("""userID""", "${buyerid}")
	.queryParam("""pageNum""", """0""")
	.queryParam("""pageSize""", """5"""))

}

class RecordedSimulation extends Simulation {
	val httpProtocol = http
	.baseURL("http://172.21.31.12:9003")
	.inferHtmlResources()
	.acceptHeader("textml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	.acceptEncodingHeader("gzip, deflate")
	.acceptLanguageHeader("zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3")
	.connectionHeader("keep-alive")
	.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0")
	val uri1 = "172.21.31.12"
	val scn = scenario("RecordedSimulation")
	.exec(Search.search)
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
