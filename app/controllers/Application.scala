package controllers

import play.api._
import play.api.libs.ws.WS
import play.api.mvc._
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object Application extends Controller {

  val randomNumUrl = "http://randnum.herokuapp.com"
  val randomWordUrl = "http://random-word.herokuapp.com/"

  def index = Action.async {
    for {
      num      <- WS.url(randomNumUrl).get().map(_.body.toInt)
      wordReqs =  Seq.fill(num)(WS.url(randomWordUrl).get().map(_.body))
      words    <- Future.sequence(wordReqs)
    } yield Ok(words.mkString(" "))
  }

}