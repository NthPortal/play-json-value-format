package com.nthportal.play.json.value

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json._

class ValTest extends FlatSpec with Matchers {
  import ValTest._

  def checkReads(method: String)(implicit reads: Reads[IntVal]): Unit = {
    s"Val.$method" should "read JSON correctly" in {
      val str = Json.toJson(1).toString()

      val from = Json.fromJson[IntVal](Json.parse(str))
      from.isSuccess shouldBe true
      from.get shouldBe IntVal(1)
    }
  }

  def checkWrites(method: String)(implicit reads: Writes[IntVal]): Unit = {
    s"Val.$method" should "write JSON correctly" in {
      val str = Json.toJson(IntVal(1)).toString()

      str shouldEqual Json.toJson(1).toString()
      val from = Json.fromJson[Int](Json.parse(str))
      from.isSuccess shouldBe true
      from.get shouldBe 1
    }
  }

  checkReads("format")(Val.format(IntVal))
  checkReads("reads")(Val.reads(IntVal))

  checkWrites("format")(Val.format(IntVal))
  checkWrites("writes")(Val.writes(IntVal))
}

object ValTest {
  case class IntVal(value: Int) extends AnyVal with Val[Int]
}
