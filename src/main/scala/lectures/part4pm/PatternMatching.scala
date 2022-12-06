package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

    // switch cases

    val rand = new Random
    val x = rand.nextInt(10)

    val description = x match {
        case 0 => "zero"
        case 1 => "one"
        case _ => "others"
    }

    println(description)

    // 1. Decompose values

    case class Person(name: String, age: Int)

    val bob = Person("Bob", 20) // companion singleton object method

    val greeting = bob match {
        case Person(n, a) if a < 21 => s"Hi, my name is $n, and I am $a years old and I can't drink in the US."
        case Person(n, a) => s"Hi, my name is $n, and I am $a years old."
        case _ => "Unknonw"
    }

    println(greeting)

    trait Expr

    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    def beautiful(expr: Expr): String = expr match {
        case Number(n) => s"$n"
        case Sum(e1, e2) => beautiful(e1) + " + " + beautiful(e2)
        case Prod(e1, e2) => {
            def prod_child(e: Expr): String = e match {
               case Sum(ne1, ne2) => "(" + beautiful(e) + ")"
                case _ => beautiful(e)
            }
            prod_child(e1) + " * " + prod_child(e2)
        } 
    }

    println(beautiful(Prod(Sum(Number(1), Number(2)), Number(3))))

    println(beautiful(Sum(Sum(Number(1), Number(2)), Number(3))))

}