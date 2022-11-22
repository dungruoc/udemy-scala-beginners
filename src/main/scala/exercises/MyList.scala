package exercices

import java.util.NoSuchElementException

abstract class MyList {

    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(element: Int): MyList
    def toString: String
}

object EmptyList extends MyList {
    override def head: Int = throw new NoSuchElementException
    override def tail: MyList = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add(element: Int): MyList = new Cons(element, this)
    override def toString: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
    override def head: Int = h
    override def tail: MyList = t
    override def isEmpty: Boolean = false
    override def add(element: Int): MyList = new Cons(element, this)
    override def toString: String = {
        if (t.isEmpty) s"${h}"
        else s"${h}, ${t}"        
    }

}

object MyListTest extends App {

    val e = EmptyList
    println(e)
    val list = e.add(1).add(2)
    println(list)
}