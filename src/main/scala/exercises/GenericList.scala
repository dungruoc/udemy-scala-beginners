package exercices.generics

import java.util.NoSuchElementException

abstract class GenericList[+A] {

    def head: A
    def tail: GenericList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): GenericList[B]
    def toString: String

    def map[B](transformer: MyTransformer[A, B]): GenericList[B]
    def filter(predicate: MyPredicate[A]): GenericList[A]
    def flatMap[B](transformer: MyTransformer[A, GenericList[B]]): GenericList[B]

    def sort(comp: (A, A) => Int): GenericList[A]
    def zipWith[B, C](list: GenericList[B], zip: (A, B) => C): GenericList[C]
    def fold[B](start: B)(operator: (B, A) => B): B
}

object Empty extends GenericList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: GenericList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[A](element: A): GenericList[A] = new Cons[A](element, this)
    override def toString: String = ""

    override def map[B](transformer: MyTransformer[Nothing, B]): GenericList[B] = this
    override def filter(predicate: MyPredicate[Nothing]): GenericList[Nothing] = this
    override def flatMap[B](transformer: MyTransformer[Nothing, GenericList[B]]): GenericList[B] = this

    override def sort(comp: (Nothing, Nothing) => Int): GenericList[Nothing] = Empty
    override def zipWith[B, C](list: GenericList[B], zip: (Nothing, B) => C): GenericList[C] = 
        if (!list.isEmpty) throw new RuntimeException("Zip of lists of different lengths")
        else Empty

    override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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

    override def map[B](transformer: MyTransformer[A, B]): GenericList[B] = {
        new Cons[B](transformer(h), t.map[B](transformer))
    }

    override def filter(predicate: MyPredicate[A]): GenericList[A] = {
        if (predicate(h)) new Cons[A](h, t.filter(predicate))
        else t.filter(predicate)
    }

    override def flatMap[B](transformer: MyTransformer[A, GenericList[B]]): GenericList[B] = {
        // a, b -> reversed a + b
        def reverse_con[T](a: GenericList[T], b: GenericList[T]): GenericList[T] = {
            if (a == Empty) b
            else reverse_con(a.tail, new Cons[T](a.head, b))
        }

        // a -> reversed a
        def reverse[T](a: GenericList[T]): GenericList[T] = {
            if (a == Empty) a
            else reverse_con(a.tail, new Cons[T](a.head, Empty))
        }

        def concat[T](a: GenericList[T], b: GenericList[T]): GenericList[T] = {
            reverse_con(reverse(a), b)
        }

        val transformed_head: GenericList[B] = transformer(h)
        val transformed_tail: GenericList[B] = t.flatMap(transformer)
        concat(transformed_head, transformed_tail)
    }

    // comp(x, y) < 0: x, y
    // comp(x, y) > 0: y, x
    override def sort(comp: (A, A) => Int): GenericList[A] = {
        if (t == Empty) this
        else {
            val sorted_tail = t.sort(comp)
            if (comp(h, sorted_tail.head) < 0) new Cons[A](h, sorted_tail)
            else new Cons(sorted_tail.head, (new Cons[A](h, sorted_tail.tail)).sort(comp))
        }
    }

    override def zipWith[B, C](list: GenericList[B], zip: (A, B) => C): GenericList[C] = {
        if (list.isEmpty) throw new RuntimeException("Lists of different lenghts")
        else new Cons(zip(h, list.head), t.zipWith[B,C](list.tail, zip))
    }

    override def fold[B](start: B)(operator: (B, A) => B): B = {
        t.fold(operator(start, h))(operator)
    }

}

trait MyPredicate[-T] {
    def apply(t: T): Boolean
}

trait MyTransformer[-A, B] {
    def apply(element: A): B
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


    class Ok[-T] extends MyPredicate[T] {
        override def apply(t: T): Boolean = true
    }

    class Id[-A] extends MyTransformer[A, String] {
        override def apply(element: A): String = s"$element"
    }

    class IsDog[-Animal] extends MyPredicate[Animal] {
        override def apply(t: Animal): Boolean = t.toString == "dog"
    }

    println(listOfCats.add(new Dog).filter(new Ok[Animal]))

    println(listOfCats.add(new Dog).filter(new IsDog[Animal]))

    // Anonymous class
    println(Empty.add(1).add(2).add(3).map(new MyTransformer[Int, Int] {
        override def apply(element: Int): Int = element * 2
    }))

    println(Empty.add("Hello Scala").add("Genric Collections").flatMap(new MyTransformer[String, GenericList[String]] {

        override def apply(text: String): GenericList[String] = {

            def split_string(splitted: Array[String], acc: GenericList[String]): GenericList[String] = {
                if (splitted.isEmpty) acc
                else split_string(splitted.tail, new Cons(splitted.head, acc))
            }

            val splitted = text.split(" ")
            split_string(splitted, Empty)
        }
    }))

    println(Empty.add(1).add(3).add(2).add(4).sort((x: Int, y: Int) => y - x))
    println(Empty.add(1).add(3).add(2).add(4).sort((x: Int, y: Int) => x - y))

    println(Empty.add(1).add(3).add(2).add(4).zipWith(
        Empty.add(0.3).add(2.0).add(4.1).add(5.12),
        (x: Int, y: Double) => {
            s"$x - $y"
        }
    ))

    println(Empty.add(1).add(3).add(2).add(4).fold("List:")(
        (x: String, y: Int) => s"$x $y"
    ))

}