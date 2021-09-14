package lectures.part3fp

object HOFsCurries extends App {
//  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // higher order function (HOF)
  // map flatMap filter in MyList

  // function that applies a function f, n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // ntB(f, n) = x => f(f(f...(x)))
  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plusTen = nTimesBetter(plusOne, 10)
  println(plusTen(1))

  // curried function
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)  // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // this vals have to have to specify types in these functions
  val stdFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")
  println(stdFormat(math.Pi))
  println(preciseFormat(math.Pi))

}

/*
* 1. Expand mylist
*   - foreach method  A => Unit - apply this function to every element in list
*     [1, 2, 3].foreach(x => println(x))
*   - sort function ((A, A) => Int) => MyList
*     [1,2,3].sort((x, y) => y - x) => [3,2,1]
*   - zipWith (list, (A, A) => B) => Mylist[B]
*     [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]
*   - fold (start)(function) => a value
*     [1,2,3].fold(0)(x + y) = 6
*
* 2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
*    fromCurry (Int => Int => Int) => (Int, Int) => Int
*
* 3. compose(f, g) => x => f(g(x))
*    andThen(f, g) => x => g(f(x))
* */