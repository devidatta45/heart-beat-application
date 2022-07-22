package persistence

import models.HeartBeatEvent
import slick.lifted.Tag
import PostgresDriver.api._

import java.time.ZonedDateTime

class HeartBeatTable(tag: Tag) extends Table[HeartBeatEvent](tag, "heart_beat") {
  def userId = column[String]("user_id", O.PrimaryKey)

  def movieId = column[String]("movie_id")

  def duration = column[Int]("duration")

  def position = column[Int]("position")

  def timestamp = column[ZonedDateTime]("timestamp")

  override def * =
    (userId, movieId, duration, position, timestamp).<>(
      (HeartBeatEvent.apply _).tupled,
      HeartBeatEvent.unapply
    )
}
