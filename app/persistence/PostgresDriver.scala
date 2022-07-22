package persistence

import com.github.tminglei.slickpg._
import models.HeartBeatEvent
import play.api.libs.json._
import protocol.HeartBeatEventJsonProtocol

import scala.reflect.ClassTag

trait PostgresDriver extends ExPostgresProfile with PgPlayJsonSupport with PgDate2Support {

  override def pgjson: String = "jsonb"

  override val api = HeartBeatApi

  object HeartBeatApi extends API with DateTimeImplicits with JsonImplicits with HeartBeatEventJsonProtocol {
    implicit val heartBeatEventColumnType = jsonMappedColumnType[HeartBeatEvent]
  }

  private def jsonMappedColumnType[T](implicit
                                      tag: ClassTag[T],
                                      baseColumnType: BaseColumnType[JsValue],
                                      reader: Reads[T],
                                      writer: Writes[T]
                                     ): BaseColumnType[T] = {
    MappedColumnType.base[T, JsValue](
      writer.writes,
      { json =>
        json.validate(reader) match {
          case JsSuccess(s, _) => s
          case _: JsError => {
            val message =
              s"""
                 Could not deserialize json to database type
                 |JSON:
                 |${Json.prettyPrint(json)}
                 """.stripMargin
            logger.error(message)
            throw new IllegalArgumentException(message)
          }
        }
      }
    )
  }
}

object PostgresDriver extends PostgresDriver