import net.exoego.facade.aws_lambda._
import typings.typedRestClient.httpClientMod.HttpClient
import upickle.default

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.JSExportTopLevel

case class HttpBinGetResponse(args: Map[String, String], headers: Map[String, String], origin: String, url: String)

object HttpBinGetResponse {
  implicit val jsonR: default.Reader[HttpBinGetResponse] = upickle.default.macroR[HttpBinGetResponse]
}

object Handler {
  @JSExportTopLevel(name = "handler")
  def handleRequest(request: APIGatewayProxyEventV2, context: Context): Promise[APIGatewayProxyResult] = {
    println(js.JSON.stringify(request))
    val pathParamsString = request.pathParameters.map(_.map { case (k, v) => s"$k=$v" }.mkString("&")).getOrElse("")
    val client           = new HttpClient("foo")
    val future = for {
      res        <- client.get(s"https://httpbin.org/get?${pathParamsString}").toFuture
      body       <- res.readBody().toFuture
      httpBinRes = upickle.default.read[HttpBinGetResponse](body)
    } yield {
      APIGatewayProxyResult(body = httpBinRes.toString, statusCode = 200)
    }
    future.toJSPromise
  }
}
