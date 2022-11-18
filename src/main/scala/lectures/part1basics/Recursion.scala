package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {


    def factorial(n: Int): Int = {

        @tailrec
        def accumFactorial(n: Int, accumulator: Int): Int = {
            if (n <= 1) accumulator
            else accumFactorial(n - 1, n * accumulator)  // TAIL Recursion
        }

        accumFactorial(n, 1)
    }

    println(factorial(5))

    def repeatedString(s: String, n: Int): String = {

        @tailrec
        def accRepeatedString(s: String, acc: String, n: Int): String = {
            if (n <= 0) acc
            else accRepeatedString(s, s + acc, n - 1)
        }

        accRepeatedString(s, "", n)
    }

    println(repeatedString("TailRecursion", 10))

    def isPrime(n: Int): Boolean = {

        @tailrec
        def notDivisibleUpTo(n: Int, t: Int): Boolean = {
            if (t <= 1) true
            else if (n % t == 0) false
                 else notDivisibleUpTo(n, t - 1)
        }

        notDivisibleUpTo(n, n / 2)
    }

    println(isPrime(2003))


    def fibonacci(n: Int): Int = {
        @tailrec
        def accFibonacci(n: Int, f1: Int, f2: Int): Int = {
            if (n <= 2) f1
            else accFibonacci(n - 1, f1 + f2, f1)
        }

        accFibonacci(n, 1, 1)
    }

    println(fibonacci(1))
    println(fibonacci(2))
    println(fibonacci(3))
    println(fibonacci(4))
    println(fibonacci(5))
    println(fibonacci(6))

}