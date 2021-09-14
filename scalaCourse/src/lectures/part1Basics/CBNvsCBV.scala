package lectures.part1Basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }

  // note the arrow
  def calledByName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
  /*
  by value: 2851348064700
  by value: 2851348064700
  by name: 2851459550300
  by name: 2851459598400
  * */

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit= println(x)
//  printFirst(infinite(), 34) // StackOverflowError
  printFirst(34, infinite())
}
