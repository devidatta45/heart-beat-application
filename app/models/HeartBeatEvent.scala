package models

import java.time.ZonedDateTime

case class HeartBeatEvent(
                           userId: String,
                           movieId: String,
                           duration: Int,
                           position: Int,
                           timestamp: ZonedDateTime
                         )
