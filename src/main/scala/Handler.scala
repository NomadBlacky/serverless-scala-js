import net.exoego.facade.aws_lambda._
import sttp.client._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

@JSExportTopLevel("Handler")
@JSExportAll
object Handler {
  implicit val sttpBackend = FetchBackend()

  // https://sttp.readthedocs.io/en/latest/backends/javascript/fetch.html#node-js
  //private val g = scalajs.js.Dynamic.global
  //g.fetch = g.require("node-fetch")
  //g.require("abortcontroller-polyfill/dist/polyfill-patch-fetch")
  //g.Headers = g.require("fetch-headers")

  def handleRequest(
      request: APIGatewayProxyEvent,
      context: Context
  ): Promise[APIGatewayProxyResult] = {
    val future = for {
      res <- quickRequest.get(uri"https://httpbin.org/get").send()
    } yield {
      APIGatewayProxyResult(200, res.body)
    }
    future.toJSPromise
  }
}
