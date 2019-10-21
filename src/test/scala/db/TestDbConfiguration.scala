package db

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait TestDbConfiguration {
  val db: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("h2")

}
