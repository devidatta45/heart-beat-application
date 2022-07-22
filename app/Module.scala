import net.codingwell.scalaguice.ScalaModule
import persistence.{ HeartBeatRepository, SlickHeartBeatRepository }
import play.api.{ Configuration, Environment }
import service.{ HeartBeatService, HeartBeatServiceImpl }

import javax.inject._

class Module(environment: Environment, configuration: Configuration)
    extends ScalaModule {

  override def configure() = {
    bind[HeartBeatRepository].to[SlickHeartBeatRepository].in[Singleton]()
    bind[HeartBeatService].to[HeartBeatServiceImpl].in[Singleton]()
  }
}
