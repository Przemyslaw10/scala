package lectures.part3fp

object MapFlatMapFilterFor extends App {
  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  // List("a1", "a2",..."d4")
  println(numbers.flatMap(number => chars.map(char => char.toString + number)))

  // foreach
  list.foreach(println)

  // for comprehensions
  // combinations
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
  } yield c.toString + n
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map(_ * 2)
  list.map {x =>
    x * 2
  }

  /*
  * 1. MyList supports for comprehensions?
  * 2. A small collection of at most ONE element - Maybe[+T]
  *   - map, flatMap, filter
  * */
}
