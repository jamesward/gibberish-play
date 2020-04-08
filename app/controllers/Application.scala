package controllers

import javax.inject.Inject
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Application @Inject()(wsClient: WSClient) extends InjectedController {

  val randomNumUrl = "https://random-num-weurlhfjnq-uc.a.run.app/"
  val randomWordUrl = "https://random-word-weurlhfjnq-uc.a.run.app/"

  def index = Action.async {
    for {
      num      <- wsClient.url(randomNumUrl).get().map(_.body.toInt)
      wordReqs =  Seq.fill(num)(wsClient.url(randomWordUrl).get().map(_.body))
      words    <- Future.sequence(wordReqs)
    } yield Ok(words.mkString(" "))
  }

}
