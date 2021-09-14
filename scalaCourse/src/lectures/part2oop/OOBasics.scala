package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet

  val counter = new Counter()
  counter.inc.inc.print
  counter.inc(10).print
}
// constructor
// class parameters are not fields - add val before name to convert to field
class Person(name: String, val age: Int = 0) {
  // body of class - can be anything here
  // any code here will be executed at instantiation
  val x = 2  //
  println(1 + 2)
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  def greet(): Unit = println(s"Hi I am $name") // name == this.name
  // same function name with different signatures (number of parameters) is allowed

  def this(name: String) = this(name, 0)  // alternative of default arguments

}

/*
* Novel and writer class
*
* Writer first name , surname, year
* method fullname
*
* Novel: name, year of release, author: Writer
*
* - authorAge - age of author in date of release
* - is written by author
* - copy: new year of release = new instance of novel
*
* */
class Writer(name: String, surname: String, val yearOfBirth: Int) {
  def fullname: String = {
    s"$name $surname"
  }
}
class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def isWrittenBy: Boolean = this.author == author
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def copy(year: Int): Novel = {
    new Novel(this.name, year, this.author)
  }
}


/*Counter class
* - receives a int value
* - method current count
* - method to increment/decrement => new counter
* - overload inc/decrement to receive an amount
* */
class Counter(val count: Int = 0) {
  def inc = {
    println("incrementing")
    new Counter(count + 1) // immutability}
  }
  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }
  def print = println(count)
}