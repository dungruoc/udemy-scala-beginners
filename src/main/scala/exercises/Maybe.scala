package exercices

abstract class Maybe[+A] {
    def isEmpty: Boolean
    def get(): A
    def map[B](f: A => B): Maybe[B]
    def flatMap[B](f: A => Maybe[B]): Maybe[B]
    def filter(f: A => Boolean): Maybe[A]
}

case object Empty extends Maybe[Nothing] {
    override def isEmpty: Boolean = true
    override def get(): Nothing = throw new RuntimeException("Empty")
    override def map[B](f: Nothing => B): Maybe[B] = Empty
    override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = Empty
    override def filter(f: Nothing => Boolean): Maybe[Nothing] = Empty
}

case class HasOne[+A](e: A) extends Maybe[A] {
    override def isEmpty = false
    override def get(): A = e
    override def map[B](f: A => B): Maybe[B] = {
        new HasOne[B](f(e))
    }
    override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(e)
    override def filter(f: A => Boolean): Maybe[A] = {
        if (f(e)) this
        else Empty
    }
}

object MaybeTest extends App {

    val x = new HasOne(3)
    println(x)
    println(x.map(e => e * 10))
   println(x.filter(e => e % 2 == 0))
}