package lectures.part1basics

object StringOps extends App {

    val aString: String = "Hello, I am learning Scala."

    println(aString.charAt(2))
    println(aString.substring(7, 11))
    println(aString.split(" ").toList)
    println(aString.startsWith("He"))
    println(aString.replace(" ", "-"))
    println(aString.toLowerCase())
}