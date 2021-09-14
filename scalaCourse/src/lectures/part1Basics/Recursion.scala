package lectures.part1Basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"computing factorial of $n - I first need fatorial of ${n - 1}")
      val result = n * factorial(n - 1)
      println(s"computed factorial of $n")
      result
    }
  }
//  println(factorial(5000))  - Exception in thread "main" java.lang.StackOverflowError
  def anotherFactorial(n: BigInt): BigInt = {
    @tailrec
    def factHelper(x: BigInt, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)  // tail recursion = use recursive call as the LAST expression
    }
    factHelper(n, 1)
  }
  /*
  anotherFactorial(10) = factHelper(10, 1)
   = factHelper(9, 10 * 1)
   = factHelper(8, 9 * 10 * 1)
   = factHelper(7, 8 * 9 * 10 * 1
   = ....
   = factHelper(2, 3 * 4 * ... * 10 * 1)
   = factHelper(1, 1 * 2 * 3, 4 * ... * 10)
   = 1 * 2 * 3 * 4 * ... * 10
  * */

  println(anotherFactorial(20000))
  // when you need loops, use tail recursion

  /*
  Exercises:
  * 1. Concatenate a string n times
  * 2. Is prime function tail recursive
  * 3. Fibonacci function tail recursive
  * */

  @tailrec
  def concatString(string: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatString(string, n - 1, string + accumulator)
  }
  println(concatString("s", 4, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)
    }
    isPrimeTailrec(n / 2, true)
  }
  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }
  println(fibonacci(8))
}
