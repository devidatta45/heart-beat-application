package service

import generator.HeartBeatEventGenerator
import models.HeartBeatEvent
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import persistence.HeartBeatRepository
import org.mockito.Mockito._
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HeartBeatServiceSpec extends PlaySpec with MockitoSugar {
  "HeartBeatService" should {
    "store heartbeat events correctly" in {
      val heartBeatRepository = mock[HeartBeatRepository]
      val heartBeatEvent =
        HeartBeatEventGenerator.heartBeatEventGen.sample.value

      val eventFuture = Future.successful(heartBeatEvent)
      when(heartBeatRepository.create(ArgumentMatchers.eq(heartBeatEvent)))
        .thenReturn(eventFuture)

      val heartBeatService = new HeartBeatServiceImpl(heartBeatRepository)

      val result = heartBeatService.registerHeartBeatEvent(heartBeatEvent)
      result.futureValue mustBe heartBeatEvent
    }
  }
}
