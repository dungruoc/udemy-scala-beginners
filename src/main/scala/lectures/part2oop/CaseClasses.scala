package lectures.part2oop

object CaseClasses extends App {

    case class Person(name: String, age: Int)

    // 1 - Class parameters are fields
    val jim = new Person("Jim", 30)
    println(jim.name)

    // 2 - sensitive toString
    println(jim)

    // 3 - equals and hashCode implemented
    val jim2 = new Person("Jim", 30)
    println(jim == jim2)

    // 4 - has a copy method
    val jim3 = jim.copy()
    println(jim3)

    // 5 - has a companion(singleton) object
    val aPerson = Person
    println(aPerson)
    val mary = Person("Mary", 23) // apply method in singleton
    println(mary)

    // 6 - Serializable

    // 7 - has extractor patterns -> can be used in pattern matching

}