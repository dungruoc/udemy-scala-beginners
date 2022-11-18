package lectures.part1basics

object DefaultArgs extends App {
    def fact(n: Int, acc: Int = 1): Int = {
        if (n <= 1) acc
        else fact(n - 1, acc * n)
    }

    println(fact(10, 1))
    println(fact(5))


    def someFunc(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = {
        println(f"someFunc: $format, $width, $height")
    }

    someFunc()
    someFunc(width=1600)
}