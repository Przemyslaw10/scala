package lectures.part4pm

import exercises.{MyList, LinkedList, Empty}

object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "the scala"
    case true => "the truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match { 
    case _ => 
  }
  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }
  // 3. Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"found $something with 2"
  }
  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => 
  }
  // PMs can be nested!
  
  // 4. case classes
  val aList: MyList[Int] = LinkedList(1, LinkedList(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case LinkedList(head, LinkedList(subhead, subtail)) =>
  }
  // 5 - list patterns
  val aStandardList= List(1,2,3,42)
  val matchAStandardList = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42 => // infix pattern
  }
  // 6 - type specifiers
  val unknown: Any = 2
  val matchUnknown = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }
  // 7 - name binding
  val nameBindingMatch = aList match {
    case notEmptyList @ LinkedList(_, _) => // name binding => use the name later(here)
    case LinkedList(1, rest @ LinkedList(2, _)) => // name binding inside nested patterns
  }
  // 8 - multi-patterns
  val multiPattern = aList match {
    case Empty | LinkedList(0, _) => // compound pattern (multi-pattern)
  }
  // 9 - if guards
  val secondElementSpecial = aList match {
    case LinkedList(_, LinkedList(specialElement, _)) if specialElement % 2 == 0 =>
  }

  /*
  * Exercise
  * */

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }
  println(numbersMatch)  // "a list of strings" ??
  // JVM trick question

}
