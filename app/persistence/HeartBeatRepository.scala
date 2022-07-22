package persistence

import com.google.inject.ImplementedBy
import models.HeartBeatEvent
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import PostgresDriver.api._
import play.api.Logger

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@ImplementedBy(classOf[SlickHeartBeatRepository])
trait HeartBeatRepository {
  def create(heartBeatEvent: HeartBeatEvent): Future[HeartBeatEvent]
}

@Singleton
class SlickHeartBeatRepository @Inject() (
    dbConfigProvider: DatabaseConfigProvider
)(implicit ec: ExecutionContext)
    extends HeartBeatRepository {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val heartBeats = TableQuery[HeartBeatTable]

  private val logger = Logger(getClass)

  override def create(
      heartBeatEvent: HeartBeatEvent
  ): Future[HeartBeatEvent] = {
    logger.trace("Saving logger event to the database")
    dbConfig.db.run(heartBeats += heartBeatEvent).map(_ => heartBeatEvent)
  }
}
