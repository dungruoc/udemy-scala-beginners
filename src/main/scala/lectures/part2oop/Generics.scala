package lectures.part2oop

object Generics extends App {

    class MyList[A] {

    }

    class MyMap[Key, Value]

    val listOfInts = new MyList[Int]
    val listOfStrings = new MyList[String]

    // generic methods

    object MyList {
        def empty[A]: MyList[A] = ???
    }

    val emptyListOfInts = MyList.empty[Int]

    // variance problem

    class Animal
    class Cat extends Animal
    class Dog extends Animal

    // 1 - List[Cat] extends List[Animal] => COVARIANCE
    class CovariantList[+A]
    val animalList: CovariantList[Animal] = new CovariantList[Cat]
    // animalList.add(new Dog) ??? is this OK

    // 2 - INVARIANCE
    class InvariantList[A]
    // val invariantList: InvariantList[Animal] = new InvariantList[Dog] => ERROR

    // 3 - CONTRAVARIANCE
    class Trainer[-A]
    val aCatTrainer: Trainer[Cat] = new Trainer[Animal]

    // bouded types

    class Cage[A <: Animal](animal: A)   // for any A subtype of Animal

    val aCage: Cage[Dog] = new Cage[Dog](new Dog)

    class NewCovariantList[+A] {
        def add[B >: A](element: B): NewCovariantList[B] = ???
    }

    val aCovariantListOfCats = new NewCovariantList[Cat]
    val aNewCovariantList = aCovariantListOfCats.add(new Dog)
}