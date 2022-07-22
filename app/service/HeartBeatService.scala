package service

import com.google.inject.ImplementedBy
import models.HeartBeatEvent
import persistence.HeartBeatRepository
import play.api.Logger

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }

@ImplementedBy(classOf[HeartBeatServiceImpl])
trait HeartBeatService {
  def registerHeartBeatEvent(
      heartBeatEvent: HeartBeatEvent
  ): Future[HeartBeatEvent]
}

@Singleton
class HeartBeatServiceImpl @Inject() (
    heartBeatRepository: HeartBeatRepository
)(implicit ec: ExecutionContext)
    extends HeartBeatService {
  private val logger = Logger(getClass)

  override def registerHeartBeatEvent(
      heartBeatEvent: HeartBeatEvent
  ): Future[HeartBeatEvent] = {
    logger.trace("Dispatching logger event to persistence")
    heartBeatRepository.create(heartBeatEvent)
  }
}
