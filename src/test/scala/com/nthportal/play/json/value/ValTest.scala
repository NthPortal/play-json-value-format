package com.nthportal.play.json.value

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json._

class ValTest extends FlatSpec with Matchers {
  import ValTest._

  "Val.reads and Val.format" should "read JSON correctly" in {
    def check(implicit reads: Reads[IntVal]): Unit = {
      val str = Json.toJson(1).toString()

      val from = Json.fromJson[IntVal](Json.parse(str))
      from.isSuccess shouldBe true
      from.get shouldBe IntVal(1)
    }

    check(Val.format(IntVal))
    check(Val.reads(IntVal))
  }

  "Val.writes and Val.format" should "write JSON correctly" in {
    def check(implicit reads: Writes[IntVal]): Unit = {
      val str = Json.toJson(IntVal(1)).toString()

      str shouldEqual Json.toJson(1).toString()
      val from = Json.fromJson[Int](Json.parse(str))
      from.isSuccess shouldBe true
      from.get shouldBe 1
    }

    check(Val.format(IntVal))
    check(Val.writes(IntVal))
  }
}

object ValTest {
  case class IntVal(value: Int) extends AnyVal with Val[Int]
}
