package lectures.part1basics

object Functions extends App {

    def aFunction(a: String, b: String): String = {
        a + " " + b
    }

    println(aFunction("hello", "functions"))

    def aRepeatedString(aString: String, n: Int): String = {
        if (n == 1) aString
        else if (n <= 0) ""
        else aString + aRepeatedString(aString, n - 1)
    }

    println(aRepeatedString("hello", 3))
    println(aRepeatedString("hello", -3))

    def greetingFunction(name: String, age: Int): String = {
        f"Hi, my name is $name and I am $age year old."
    }

    println(greetingFunction("Scala", 1))

    // 1 * 2 * ... n
    def factorial(n: Int): Int = {
        if (n <= 1) 1
        else n * factorial(n - 1)
    }
    println(factorial(10))

    def fibonacci(n: Int): Int = {
        if (n <= 2) 1
        else fibonacci(n - 1) + fibonacci(n - 2)
    }

    println(fibonacci(5))

    def isPrime(n: Int): Boolean = {
        def notDivisibleUpTo(n: Int, t: Int): Boolean = {
            if (t <= 1) true
            else if (n % t == 0) false
                 else notDivisibleUpTo(n, t - 1)
        }

        notDivisibleUpTo(n, n / 2)
    }

    println(isPrime(2003))
    println(isPrime(97))
    println(isPrime(14))
}