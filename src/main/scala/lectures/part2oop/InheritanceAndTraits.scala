package lectures.part2oop

object InheritanceAndTraits extends App {

    class Animal {
        def eat = println("eating")
    }

    class Cat extends Animal

    val aCat = new Cat
    aCat.eat


    // constructors
    class Person(name: String, age: Int) {
        def this(name: String) = this(name, 0)
    }
    class Adult(name: String, age: Int, idCard: Int) extends Person(name, age)

    // overriding

    class Dog extends Animal {
        override def eat = println("Dog eating")
    }

    val aDog = new Dog
    aDog.eat

    val anUnknownAnimal: Animal = new Dog
    anUnknownAnimal.eat

    // Preventing override
    // 1 - use keyword final on the members
    // 2 - use final on the entire class to prevent inheriting
    // 3 - sealed keyword on class: class can be extends within the same scala file, but not elsewhere
}