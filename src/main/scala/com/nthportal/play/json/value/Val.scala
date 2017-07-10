package com.nthportal.play.json.value

import play.api.libs.json._

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
trait Val[A] extends Any {
  self: AnyVal =>
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
    * Returns a [[`Reads`]] for a [[`Val`]], which treats the `Val` as
    * its contained value.
    *
    * @param f a function for constructing a `Val` from a value
    * @tparam A the type of the contained value
    * @tparam V the type of the `Val`
    * @return a `Reads` for the specified `Val`
    */
  def reads[A: Reads, V <: Val[A]](f: A => V): Reads[V] = implicitly[Reads[A]] map f

  /**
    * Returns a [[`Writes`]] for a [[`Val`]], which treats the `Val` as
    * its contained value.
    *
    * @note
    *
    * The function `f` is not used to construct the `Writes`; merely to
    * drive type inference. This method is to mirror [[reads]] and [[format]],
    * and to make invocations look cleaner.
    *
    * When a case class is used for the `Val`, such as the one that follows:
    *
    * {{{
    * case class Foo(value: Bar) extends AnyVal with Val[Bar]
    * }}}
    *
    * the class's companion object can be used for the function `f`, yielding
    * the invocation `Val.writes(Foo)`. This conveys the meaning of the method
    * invocation well, and is less awkward than `Val.writes[Bar, Foo]`.
    *
    * @param f a function for constructing a `Val` from a value
    * @tparam A the type of the contained value
    * @tparam V the type of the `Val`
    * @return a `Writes` for the specified `Val`
    */
  @inline
  def writes[A: Writes, V <: Val[A]](f: A => V): Writes[V] = writes[A, V]

  /**
    * Returns a [[`Writes`]] for a [[`Val`]], which treats the `Val` as
    * its contained value.
    *
    * @tparam A the type of the contained value
    * @tparam V the type of the `Val`
    * @return a `Writes` for the specified `Val`
    */
  def writes[A: Writes, V <: Val[A]]: Writes[V] = (o: V) => implicitly[Writes[A]] writes o.value

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
