package exercices.generics

import java.util.NoSuchElementException

abstract class GenericList[+A] {

    def head: A
    def tail: GenericList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): GenericList[B]
    def toString: String
}

object Empty extends GenericList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: GenericList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[A](element: A): GenericList[A] = new Cons[A](element, this)
    override def toString: String = ""
}

class Cons[+A](h: A, t: GenericList[A]) extends GenericList[A] {
    override def head: A = h
    override def tail: GenericList[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](element: B): GenericList[B] = new Cons[B](element, this)
    override def toString: String = {
        if (t.isEmpty) s"${h}"
        else s"${h}, ${t}"
    }
}

object GenericList extends App {

    val aListOfString = new Cons("a", new Cons("b", new Cons("c", Empty)))
    println(aListOfString.add("d"))

    class Animal {
        override def toString: String = "animal"
    }

    class Cat extends Animal {
        override def toString: String = "cat"
    }

    class Dog extends Animal {
        override def toString: String = "dog"
    }

    val listOfAnimal: GenericList[Animal] = Empty
    println(listOfAnimal.add(new Animal).add(new Dog).add(new Cat))

    val listOfCats: GenericList[Cat] = new Cons(new Cat, new Cons(new Cat, new Cons(new Cat, Empty)))
    println(listOfCats.add(new Dog))
}