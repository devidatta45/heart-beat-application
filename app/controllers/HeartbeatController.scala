package controllers

import models.HeartBeatEvent
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import protocol.HeartBeatEventJsonProtocol
import service.HeartBeatService

import javax.inject.Inject
import scala.concurrent.ExecutionContext

/** Takes HTTP requests and produces JSON.
  */
class HeartbeatController @Inject() (heartBeatService: HeartBeatService)(
    implicit ec: ExecutionContext
) extends InjectedController
    with HeartBeatEventJsonProtocol {

  private val logger = Logger(getClass)

  def process: Action[HeartBeatEvent] =
    Action.async(parse.json[HeartBeatEvent]) { implicit request =>
      logger.trace("Register heart beat event: ")
      heartBeatService.registerHeartBeatEvent(request.body).map { post =>
        Created(Json.toJson(post))
      }
    }
}
