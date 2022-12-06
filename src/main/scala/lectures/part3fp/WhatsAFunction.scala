package lectures.part3fp

object WhatsAFunction extends App {
    
    val aFunc = new MyFunction[Int, Int] {
        override def apply(in: Int): Int = {
            in * in
        }
    }

    println(aFunc(2))

    // scala built-in function types
    // Function1, ... Function22
    val aStringToInt = new Function1[String, Int] {
        override def apply(str: String): Int = {
            str.toInt
        }
    }

    println(aStringToInt("100"))

    val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
        override def apply(a: Int, b: Int): Int = a + b
    }

    println(adder(1, 2))

    val superAdder: (Int => (Int => Int)) = new (Int => (Int => Int)) {
        override def apply(num: Int): (Int => Int) = new Function1[Int, Int] {
            override def apply(in: Int) = in + num
        }
    }

    val add3 = superAdder(3)
    println(add3(5))
}

trait MyFunction[A, B] {
    def apply(in: A): B
}