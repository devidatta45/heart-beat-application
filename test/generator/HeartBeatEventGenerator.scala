package generator

import models.HeartBeatEvent
import org.scalacheck.Gen

import java.time.ZonedDateTime

object HeartBeatEventGenerator {

  def heartBeatEventGen: Gen[HeartBeatEvent] = for {
    userId <- Gen.alphaStr
    movieId <- Gen.alphaStr
    duration <- Gen.choose(100, 500)
    position <- Gen.choose(10, 200)
  } yield HeartBeatEvent(
    userId,
    movieId,
    duration,
    position,
    ZonedDateTime.now()
  )
}
