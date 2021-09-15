package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = Tuple2(2, "Hello, Scala")  // Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "Hello, Scala")  // Tuple2[Int, String] = (Int, String)
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // swaps elements in place
  
  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), ("Daniel", 789)).withDefaultValue(-1)  // Map("Jim" -> 555, "Daniel" -> 789)
  // a -> b is sugar  for (a, b)
  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))  // default val
  
  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map flatMap and filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")))
  
  // mapValues
  println(phoneBook.mapValues(number => number * 10))

  // conversion to other collections
  println(phoneBook.toList)
  println(List(("Jim",555), ("Daniel",789)).toMap)

  val names = List("Bob", "James", "Angela", "Marty", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  // 1)
  val badMap = Map("Jim" -> 555, "JIM" -> 900)
  println(badMap.map(x => x._1.toUpperCase -> x._2))  // last value wins (overwrites previous)


  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }
  def friend(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val friendsA = network(person)
    val friendsB = network(friend)
    network + (person -> (friendsA + friend)) + (friend -> (friendsB + person))
  }

  def unfriend(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val friendsA = network(person)
    val friendsB = network(friend)
    network + (person -> (friendsA - friend)) + (friend -> (friendsB - person))
  }

  def noOfFriends(network: Map[String, Set[String]], person: String): Int = {
    network(person).size
  }
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }
  def mostLikedPerson(network: Map[String, Set[String]]): String = {
//    network.map(pair => (pair._1, pair._2.size)).fold("" -> 0){
//      (x, y) => {
//        if (y._2 > x._2) y
//        else x
//      }
//    }._1
    network.maxBy(pair => pair._2.size)._1
  }
  def countOfFriendless(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else if (person == target) true
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }


  /*
  * 1. What would happen if I had two original entries "Jim" -> 555 and "JIM" ->900?
  * 2. Overly simplified social network based on maps
  *  Person = String
  *  - add a person to the network
  *  - remove
  *  - friend (mutual)
  *  - unfriend
  *  - numbers of friends of person
  *  - person with most friends
  *  - how many people have NO friends
  *  - if there is a social connection between two people (direct or not)
  * */

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testnet = friend(jimBob, "Bob", "Mary")
  val testnet2 = add(testnet, "friendless Joe")
  println(testnet)
  println(noOfFriends(testnet, "Bob"))
  println(mostLikedPerson(testnet))
  println(countOfFriendless(testnet2))
  //  sn.friend("Mary", "Friend of Mary")
//  sn.friend("Mary", "Friend2 of Mary")
//  sn.friend("Mary", "Friend3 of Mary")
//
//  sn.add("Joe")
//  sn.add("Joe2")
//  sn.add("Joe3")
//  sn.add("Joe4")
//  println(sn.unfriend("Mary", "Friend3 of Mary"))
//  println(sn.mostLikedPerson())
//  println(sn.countOfFriendless())
}
