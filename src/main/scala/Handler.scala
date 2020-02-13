import net.exoego.facade.aws_lambda._

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.JSExportTopLevel

object Handler {
  @JSExportTopLevel("handleRequest")
  def handleRequest(
      request: APIGatewayProxyEvent,
      context: Context
  ): Promise[APIGatewayProxyResult] =
    Future {
      APIGatewayProxyResult(200, "okkkkkkkkkey")
    }.toJSPromise
}
