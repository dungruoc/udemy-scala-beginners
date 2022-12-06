package lectures.part3fp

import scala.util.{Try, Success, Failure, Random}

object HandlingFailure extends App {

    val aFailure = Failure(new RuntimeException("FAILED"))

    println(aFailure)

    def unsafeMethod(): String = throw new RuntimeException("No String for you, buster")

    val potentialFailure = Try(unsafeMethod())
    println(potentialFailure)
    println(potentialFailure.isSuccess)

    def backupMethod(): String = "Some default backup"

    val fallbackTry = potentialFailure.orElse(Try(backupMethod))
    println(fallbackTry)

    // Use case

    class Connection {
        def get(url: String): String = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) "<html> Page </htlm>"
            else throw new RuntimeException("Connection lost")
        }

        def getSafe(url: String): Try[String] = Try(get(url))
    }

    object HttpService {
        def getConnection(host: String, port: String): Connection = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) new Connection
            else throw new RuntimeException("No Connection")
        }

        def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    }

    val hostName = "abc.com"
    val port = "3000"

    val possibleConnection = HttpService.getSafeConnection(hostName, port)
    val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/index.html"))
    def renderHtml(content: String) = println(content)

    possibleHtml.foreach(renderHtml)

    HttpService.getSafeConnection(hostName, port)
        .flatMap(conn => conn.getSafe("/index.html"))
        .foreach(renderHtml)

    // for comprehention
    for {
        conn <- HttpService.getSafeConnection(hostName, port)
        page <- conn.getSafe("/index.html")
    } renderHtml(page)
}