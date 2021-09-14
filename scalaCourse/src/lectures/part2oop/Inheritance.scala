package lectures.part2oop

object Inheritance extends App {

  // single class inheritance - you can only subclass one class
  class Animal {
    val creatureType = "wild"
//    private def eat = println("nomnom") // private def will make this accesible only from Animal class
    def eat: Unit = println("nomnom") // protected def will make this accesible in sublasses
//    def eat = println("nomnom") // public
    // private, protected, by default - public
  }
  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("crunch crunch")
    }
  }
  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType = "domestic"
    override def eat: Unit = {
      super.eat // method eat in super class: "nomnom"
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding - supplying different methods between derived classes
  // overloading - defining method with the same name but different parameters (number of params)

  // preventing overrides
  // 1 - use final on member (final def ...)
  // 2 - use final on entire class (prevents subclassing)
  // 3 - seal the class = extend classes in THIS file, prevent extensions in OTHER files
  //

}
