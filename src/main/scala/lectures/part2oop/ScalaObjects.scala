package lectures.part2oop

object ScalaObjects {

    object Earth { // Scala SINGLETON INSTANCE
        val shape = "Sphere"

        def spin: Boolean = true
    }

    println(Earth.shape)
    println(Earth spin)


    // COMPANIONS pattern: single ton + class of a same name in a same scope
    object Person {
        val N_EYES = 2
        def canFly = false

        // Factory method
        def apply(mother: Person, father: Person): Person = new Person(s"Child of ${mother.name} and ${father.name}")
    }

    class Person(val name: String) {

    }


    // Scala App is an object with a method
    // main(args: Array[String]): Unit
    def main(args: Array[String]): Unit = {
        println(Person.N_EYES)
        println(Person canFly)

        val person1 = Person
        val person2 = Person
        println(person1 == person2)

        val mary = new Person("Mary")
        val john = new Person("John")

        println(mary == john)

        val aChild = Person(mary, john)
        println(aChild name)
    }

}