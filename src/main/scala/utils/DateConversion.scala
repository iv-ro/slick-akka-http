package utils

import io.circe.{Decoder, Encoder, Json}
import org.joda.time.DateTime

trait DateConversion {

  implicit val decoder: Decoder[DateTime] = io.circe.Decoder.decodeString.emap[DateTime](str => Right(DateTime.parse(str)))

  implicit val encoder: Encoder[DateTime] = io.circe.Encoder.instance(d => Json.fromString(d.toString("yyyy-MM-dd")))

}
