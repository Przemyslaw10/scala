package lectures.part2oop

object Objects extends App {
  // scala does not have class-level functionality ("static")
  object Person {  // objects do not receive parameters, it is type + its only instance
    // static/class - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  // object Person and class Person are "companions"
  class Person(val name: String) {
    // instance level functionality

  }
  println(Person.N_EYES)
  println(Person.canFly)
  // scala object is singleton instance
  val person1 = Person
  val person2 = Person
  println(person1 == person2)  // true

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john) // true
  val Bobby = Person(mary, john)

  /*
   Scala Applications = Scala object with
   def main(args: Array[String]): Unit
  */
}
