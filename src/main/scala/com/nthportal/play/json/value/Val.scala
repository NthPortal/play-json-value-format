package com.nthportal.play.json.value

import play.api.libs.json.{Format, JsResult, JsValue}

/**
  * A value class whose value is accessible as the field `value`.
  *
  * For example:
  *
  * {{{
  * case class IntVal(value: Int) extends AnyVal with Val[Int]
  *
  * case class StringVal(value: String) extends AnyVal with Val[String]
  * }}}
  *
  * @tparam A the type of the value
  */
trait Val[A] extends Any { self: AnyVal =>
  /**
    * Returns the contained value.
    *
    * @return the contained value
    */
  def value: A
}

/**
  * A utility object for creating [[Format]]s for [[Val]]s.
  */
object Val {
  /**
    * Returns a [[`Format`]] for a [[`Val`]], which treats the `Val` as
    * its contained value.
    *
    * @param f   a function for constructing a `Val` from a value
    * @param fmt a `Format` for the value contained in the `Val`
    * @tparam A the type of the contained value
    * @tparam V the type of the `Val`
    * @return a `Format` for the specified `Val`
    */
  def format[A, V <: Val[A]](f: A => V)(implicit fmt: Format[A]): Format[V] = new Format[V] {
    override def writes(o: V): JsValue = fmt writes o.value

    override def reads(json: JsValue): JsResult[V] = fmt map f reads json
  }
}
