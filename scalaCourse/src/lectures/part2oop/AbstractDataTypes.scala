package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "Wild"
    def eat: Unit
  }
  class Dog extends Animal {
    override val creatureType: String = "Canine"  // override is optional if val is abstract (not defined value)
    def eat: Unit = println("Crunch crunch")

  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    val eat: Unit = "nomnomonom"
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits dont have constructor parameters
  // 2 - multiple traits may be inherited by the same class (one class can inherit one class but multi traits)
  // 3 - traits = behavior, abstract class = "thing"
}
