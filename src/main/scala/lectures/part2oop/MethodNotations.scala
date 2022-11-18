package lectures.part2oop

object MethodNotations extends App {

    class Person(val name: String, favouriteMovie: String, val age: Int =  0) {
        def likes(movie: String): Boolean = (movie == favouriteMovie)
        def +(someone: Person): String = s"${this.name} is hanging out with ${someone.name}"

        def +(nickName: String): Person = {
            new Person(s"$name ($nickName)", favouriteMovie)
        }

        def unary_+ : Person = {
            new Person(name, favouriteMovie, age + 1)
        }

        def learns(subject: String): String = {
            s"$name learns $subject."
        }

        def learnsScala: String = {
            this.learns("Scala")
        }

        def apply(times: Int): String = {
            s"$name watched $favouriteMovie $times times."
        }
    }

    val mary = new Person("Mary", "Inception")

    println(mary likes "Inception")

    val bob = new Person("Bob", "Fight Club")

    println(mary + bob)

    println(mary + "the rock star" name)

    println(+mary age)

    println(mary.learnsScala)

    println(mary(3))
}