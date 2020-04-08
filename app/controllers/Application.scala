package controllers

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class Application @Inject()(wsClient: WSClient, config: Configuration) extends InjectedController {

  val randomNumUrl = config.get[String]("random.num.url")
  val randomWordUrl = config.get[String]("random.word.url")

  def index = Action.async {
    for {
      num      <- wsClient.url(randomNumUrl).get().map(_.body.toInt)
      wordReqs =  Seq.fill(num)(wsClient.url(randomWordUrl).get().map(_.body))
      words    <- Future.sequence(wordReqs)
    } yield Ok(words.mkString(" "))
  }

}
