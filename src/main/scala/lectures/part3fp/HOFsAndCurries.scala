package lectures.part3fp

object HOFsAndCurries extends App {


    def nTimes(f: Int => Int, n: Int, x: Int): Int = {
        if (n <= 0) x
        else nTimes(f, n - 1, f(x))
    }

    println(nTimes((x: Int) => x + 1, 3, 1))

    // take a function and create a function that apply N times
    def superNTimes(f: Int => Int, n: Int): Int => Int = {
        if (n <= 1) f
        else superNTimes(((g: Int => Int) =>
                             {(x: Int) => f(g(x))})
                             (f),
                        n - 1)
    }

    println(superNTimes((x: Int) => x + 1, 3)(1))
    println(superNTimes((x: Int) => x + 1, 3)(5))


    // Curried

    def curriedFormatter(f: String)(x: Double): String = f.format(x)

    val standardFormatter: Double => String = curriedFormatter("%4.2f")
    val longFormatter: Double => String = curriedFormatter("%10.8f")
    println(standardFormatter(Math.PI))
    println(longFormatter(Math.PI))

    // f(x, y) -> g(x)(y)
    def toCurry(f: (Int, Int) => Int): Int => (Int => Int) = {
        (x: Int) => {
            (y: Int) => f(x, y)
        }
    }

    println(toCurry((x: Int, y: Int) => x - y)(4)(3))
}