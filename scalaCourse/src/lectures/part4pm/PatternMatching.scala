package lectures.part4pm
import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random()
  val x = random.nextInt(10)
  val description = x match {
    case 1 => "the ONE"
    case 2 => "double"
    case 3 => "third"
    case _ => "something else"  // wildcard - will match anything
  }
  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
//    case _ => s"I don't know who I am"
  }
  println(greeting)
  /*
  * 1. cases are matched in order - first pattern that matches wins
  * 2. what if no cases match? MatchError
  * 3. What is the type of match expression? Unifies to lowest common ancestor of all possible cases
  * 4. PM works really well with case classes
  * */

  // 2. PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("york")
  animal match {
    case Dog(someBreed) => println(s"Match a dog of $someBreed breed")
  }

  // don't match everything
  val isEven = x % 2 == 0

  /*
  * Exercises
  * simple function with PM
  * takes and Expr => human readable form
  * Sum(Number(2), Number(3)) => "2 + 3"
  * Sum(Number(2), Number(3), Number(4)) => "2 + 3 + 4"
  * Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
  * Sum(Prod(Number(2), Number(1)), Number(3))
  * */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = {
    def maybeShowParentheses(expr: Expr) = {
      expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => s"(${show(expr)})"
      }
    }
    expr match {
      case Sum(no1, no2) => s"${show(no1)} + ${show(no2)}"
      case Prod(no1, no2) => s"${maybeShowParentheses(no1)} * ${maybeShowParentheses(no2)}"
      case Number(n) => s"$n"
    }
  }
  println(show(Sum(Number(2), Number(3))))
  //  2 + 3
  println(show(Sum(Number(2), Sum(Number(3), Number(4)))))
  //  2 + 3 + 4
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(5)))))
  //  (2 + 1) * (3 + 5)
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
  //  2 * 1 + 3
}
