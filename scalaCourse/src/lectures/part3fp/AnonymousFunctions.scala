package lectures.part3fp

object AnonymousFunctions extends App {
  //  val doubler = new Function1[Int, Int] {
  //    override def apply(x: Int): Int = x * 2
  //  }
  // ^^ instead of this, we do this:
  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  //  val doubler: Int => Int = x => x * 2
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // with lambdas () are necessary to call a function
  println(justDoSomething)
  println(justDoSomething())

  // curly braces with Lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // syntactic sugars
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b
  /*
  * 1. MyList replace all functionx calss with lambdas
  * 2. Rewrite the special adder as anonymous function
  *
  * */
}
