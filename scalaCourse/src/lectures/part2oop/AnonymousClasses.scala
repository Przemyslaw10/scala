package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }
  class AnonymousClasses$$anon$1 extends Animal {
    override  def eat: Unit = println("ahahahah")
  }
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahah")
  }
  /*
  equivalent with

  class AnonymousClasses$$anon$1 extends Animal {
    override  def eat: Unit = println("ahahahah")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
  * */
  println(funnyAnimal.getClass)
  class Person(name: String) {
    def sayHi: Unit = println(s"Hi my name is $name how can i help")
  }
  val jim = new Person("Jim") {  // need to pass constructor arguments if necessary
    override def sayHi: Unit = println(s"Hi my name is $this.name how can i be of service")
  }
}
