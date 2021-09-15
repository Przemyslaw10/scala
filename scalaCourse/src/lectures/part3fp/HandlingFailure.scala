package lectures.part3fp

import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter

import scala.util.{Failure, Random, Success, Try}


object HandlingFailure extends App {
  // create success adn failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Runtime exc"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string")
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }
  // utilities
  println(potentialFailure.isSuccess)
  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("a valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()


  // map flatMap filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // -> for comprehensions

  /*
  * Exercise
  * */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }
  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Some one else took the port")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }
  // if you get the HTML page from conection, print it to the console i.e. call renderHTML

  Try {
    val connection = HttpService.getConnection(hostname, port)
    val html = connection.get("example.com")
    renderHTML(html)
  }

  val connectionSafe = HttpService.getConnectionSafe(hostname, port)
  val possibleHtml = connectionSafe.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML)
  
  // shorter version - chained
  HttpService.getConnectionSafe(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for version
  for {
    connection <- HttpService.getConnectionSafe(hostname, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  /*
  * try {
  *  connection = http...
  *   try {
  *     connection.get("home")
  *     renderHtml(page)
  *   } catch someOtherExc
  * } catch exception
  * */
}
