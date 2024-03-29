package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // function types Function2[A, B, R] == (A, B) => R
  // all scala functions are objects

  // 1)
  /*
    val concatenate: (String, String) => String = new Function2[String, String, String] {
      override def apply(v1: String, v2: String): String = v1 + v2
    }
  */
  // or
  val concatenate: (String, String) => String = (v1: String, v2: String) => v1 + v2
  // 3)
  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(8))  // curried function

  val superAdderAnon = (x: Int) => (y: Int) => x + y
}

trait MyFunction[A, B] {
  def apply(element: A): B = ???
}

/*
* 1. define a function that takes 2 strings and concatenates them
* 2. transform Mypredicate and mytransformer into function types
* 3. define a function which takes an int and returns another function which takes an int and returns an int
*   - what's the type of this function
*   - how to do it
* */