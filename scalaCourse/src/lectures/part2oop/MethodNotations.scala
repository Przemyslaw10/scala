package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} hangouts with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)
    def unary_! : String = s"$name what the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and my fav movie is $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
    def learns(language: String): String = s"$name is learning $language"
    def learnsScala: String = this learns "Scala"
  }
  val mary = new Person("Mary", "Inception", 21)
  println(mary.likes("Inception"))
  println(mary likes "Inception")  // infix notation = operator notation
  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club", 23)
  println(mary + tom)
  println(mary.+(tom))
  println(1 + 2)
  println(1.+(2))
  // all operators are methods

  // akka actors have operators like ! and ?

  // prefix notation
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)  // not preferred

  // apply method name
  println(mary.apply())
  println(mary())  // the same effect

  /*
  * 1. Overload the + operator which receives a string and returns new person with nickname
  *   mary + "the rockstar" => New person "Mary (the rockstar)"
  *
  * 2. Add an age to the Person class
  *   Add unary + opperator => new Person with age + 1
  *
  * 3. Add a "learns" method in person class => "Mary learns scala"
  *   add a learnsScala method without params calls learns method with scala as parameter
  * use postfix notation
  *
  * 4. Overload apply method
  * mary.apply(2) => "Mary watched Inception 2 times"
  * */
  println((mary + "the rockstar")())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(10))
}
