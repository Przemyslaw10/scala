package lectures.part2oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)
  // 1. class parameters are fields (no need to write val age = ..)
  val jim = Person("Jim", 24)
  println(jim.age)

  // 2. sensible toString
  println(jim.toString)
  println(jim)  // jim == jim.toString

  // 3. equals and hashCode implemented already
  val jim2 = Person("Jim", 24)
  println(jim == jim2)  // true (without case it would be FALSE)

  // 4. CCs have handy copy method
  val jim3 = jim.copy()
  println(jim3)

  // 5. Have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // instead of new Person

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in pattern matching

  case object UnitedKingdom {
    def name: String = "the UK of GB and NI"
  }


/*
* exercise
* Expand MyList - use case classes and case objects
*
* */

}
