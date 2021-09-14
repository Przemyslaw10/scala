package lectures.part1Basics

object Functions extends App {
  def aFunction(a: String, b: Int): String  = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction)
  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n - 1)
  }

  /*
  * 1. A greeting function (name, age) -> "Hi, my name is $name and I am $age years old"
  * 2. Factorial function product of int numbers up to number passed
  * 3. Fibonacci 1, 1, 2, 3, 5, 8, ... , f(n - 1) + f(n - 2).
  * 4. Function that tests if number is prime.
  * */

  def aGreeting(name: String, age: Int): String = {
    s"Hi, my name is $name and I am $age years old"
  }
  println(aGreeting("Przemek", 26))

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else factorial(n - 1) * n
  }
  println(factorial(5))

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n/2)
  }
  println(isPrime(37))
}
