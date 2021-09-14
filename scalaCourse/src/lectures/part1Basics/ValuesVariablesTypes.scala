package lectures.part1Basics

object ValuesVariablesTypes extends App {
  val x: Int = 42  // type of val is optional `val x = 42` is valid. Compiler can infer type
  println(x)
//  x = 2 WRONG! vals are immutable

  val aString: String = "Hello"
  val anotherString = "goodbye"
  val aBoolean: Boolean = true // or false
  val aChar: Char = 'a' // single quotes
  val anInt: Int = x
  val aShort: Short = 4213
  val aLong: Long = 47139751297L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  // variables
  var aVariable: Int = 21
  aVariable = 5  // side effects


}
