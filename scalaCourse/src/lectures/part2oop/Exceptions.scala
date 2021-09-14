package lectures.part2oop

object Exceptions extends App {
//  val x: String = null
//  println(x.length)
// ^^ NullPointerException

  // 1. Throwing exception
  //  val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend Throwable class.
  // Exceptions and Error are the major Throwable subtypes

  // 2. how to catch exception
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No int for you")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43// println("caught Runtime exception")
  } finally {
    // code that will get executed no matter what
    // finally block is optional
    // it does not influence return type of this expression
    // use this only for side effects

    println("finally")
  }
  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
//  throw exception

  /*
  * 1. Crash program with an OutOfMemoryError
  * 2. Crash with StackOverflowError
  * 3. PocketCalculator
  *   - add(x, y) -- int
  *   - subtract(x, y) -- int
  *   - multiply(x,y)
  *   - divide(x,y)
  * Throw
  * OverflowException if add(x,y) exceeds Int.MAX_VALUE
  * UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
  * MathCalculationException
  * */
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  // OMM
  // val array = Array.ofDim(Int.MaxValue)
  // StackOverflowException
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

//  println(PocketCalculator.multiply(12431241, 234121412))
//  println(PocketCalculator.divide(1, 0))
}
