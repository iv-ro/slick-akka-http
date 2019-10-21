package application

import java.time.Duration

import com.typesafe.config.{Config, ConfigFactory}

trait Configuration {
  protected val config : Config = ConfigFactory.load()
  private val databaseConfig = config.getConfig("database")
  private val httpConfig = config.getConfig("http")

  val jdbcUrl: String = databaseConfig.getString("db.url")
  val dbUser: String = databaseConfig.getString("db.user")
  val dbPassword: String = databaseConfig.getString("db.password")

  val httpHost: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")
  val httpSelfTimeout: Duration = httpConfig.getDuration("self-timeout")

}
