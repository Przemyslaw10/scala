package lectures.part1Basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def trFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFactorial(n - 1, n * acc)
  }
  val fact10 = trFactorial(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")
  savePicture(width = 800)
  /*
  Either we pass every leading argument
    we can also name the arguments
    */
}
