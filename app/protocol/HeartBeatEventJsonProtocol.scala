package protocol

import models.HeartBeatEvent
import play.api.libs.json.{Json, OFormat}

trait HeartBeatEventJsonProtocol {
  implicit val heartBeatEventFormat: OFormat[HeartBeatEvent] = Json.format[HeartBeatEvent]
}
