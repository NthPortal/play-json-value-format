package com.nthportal.play.json.value

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json._

class ValTest extends FlatSpec with Matchers {
  import ValTest._

  behavior of "Val.format"

  it should "write JSON correctly" in {
    implicit val fmt = Val.format(IntVal)

    val str = Json.toJson(IntVal(1)).toString()

    str shouldEqual Json.toJson(1).toString()
    val from = Json.fromJson[Int](Json.parse(str))
    from.isSuccess shouldBe true
    from.get shouldBe 1
  }

  it should "read JSON correctly" in {
    implicit val fmt = Val.format(IntVal)

    val str = Json.toJson(1).toString()

    val from = Json.fromJson[IntVal](Json.parse(str))
    from.isSuccess shouldBe true
    from.get shouldBe IntVal(1)
  }

}

object ValTest {
  case class IntVal(value: Int) extends AnyVal with Val[Int]
}
