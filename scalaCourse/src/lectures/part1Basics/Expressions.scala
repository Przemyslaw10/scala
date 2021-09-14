package lectures.part1Basics

object Expressions extends App {
  val x = 1 + 2  // expression
  println(x)
  println(2 + 3 * 4)
  // + - * / & | ^ << >>
  // >>> right shift with zero extension
  println(1 == x)
  // == != > >= < <=
  println(!(x == 1))
  // ! && ||
  var aVariable = 2
  aVariable += 3 // this works with -= *= /=  ... this are all side effects
  println(aVariable)

  // instructions (DO) vs expressions (has a value and type, are evaluated - functional programming)
  // if expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3  // if expression
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  //  dont do while loops !!
//  var i = 0
//  while (i < 10) {
//    println(i)
//    i += 1
//  }

  // everything in scala is expression
  val aWeirdValue = (aVariable = 3)
  println(aWeirdValue) // ()
  // side effects: println(), while loop, reassigning = they are expressions returning Unit

  // code blocks
  val aCodeBlock = {
    // these values are visible only in code block namespace
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"  // value of code block is its last expression
  }
  // 1. what is the difference between "hello world" and println("hello world")
  // first has type String, second has type Unit and side effect (printing to console)
  // 2.
  val someValue = {
    2 < 3
  }
  // someValue: Boolean = true
  val someOtherValue = {
    if(someValue) 239 else 906
    42
  }
  // someOtherValue = 42
}
