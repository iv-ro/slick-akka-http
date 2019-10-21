package application

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait DbConfiguration {
  lazy val db = DatabaseConfig.forConfig[JdbcProfile]("pgdb")
}
