package exercises

abstract class MyList[+A] {
  /*
  head = return int - first element of the list
  tail = remainder of the list
  isEmpty = is this list empty? -> bool
  add(int) => new list with this element added
  toString => a string representation of the list
  * */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def foreach(function: A => Unit): Unit
  // concat
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = LinkedList(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def foreach(function: Nothing => Unit): Unit = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class LinkedList[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = LinkedList(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) LinkedList(h, t.filter(predicate))
    else t.filter(predicate)
  }
  def map[B](transformer: A => B): MyList[B] = {
    LinkedList(transformer(h), t.map(transformer))
  }
  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }
  def foreach(function: A => Unit): Unit = {
    function(h)
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = LinkedList(h, t ++ list)
}

/*
* 1. create a generic trait MyPredicate[-T] with a little method test(B) => Boolean
*   - if T passes condition
* 2. Generic trait MyTransformer[-A, B] with method transform(A) => B
* 3. MyList:
*   - map(transformer) => MyList
*   - map(predicate) => MyList
*   - flatMap(transformer from A to MyList[B] => MyList[B]
*
* class EvenPredicate extend MyPredicate[Int]
* class StringToIntTransformer extends MyTransformer[String, Int]
*
* [1,2,3].map(n * 2) = [2, 4, 6]
* [1,2,3,4].filter(n % 2) = [2,4]
* [1,2,3].flatMap(n => [n, n + 1] => [1,2,2,3,3,4]
*
* */

object ListTest extends App {
  //  val list = new LinkedList(1, new LinkedList(2, new LinkedList(3, Empty)))
  //  println(list.tail.head)
  //  println(list.add(4).head)
  //  println(list.toString)

  // generic
  val listOfIntegers: MyList[Int] = LinkedList(1, LinkedList(2, LinkedList(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = LinkedList(4, LinkedList(5, Empty))
  val listOfStrings: MyList[String] = LinkedList("Hello", LinkedList("Scala", Empty))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(_ * 2)).toString

  println(listOfIntegers.filter(_ % 2 == 0)).toString
  println(listOfIntegers ++ anotherListOfIntegers).toString
  println(listOfIntegers.flatMap((element: Int) => LinkedList(element, LinkedList(element + 1, Empty))))
}