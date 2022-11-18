package lectures.part1basics


object CBNvsCBV extends App {

    def callByValue(x: Long): Unit = {
        println("by value:" + x)
        println("by value:" + x)
    }

    def callByName(x: => Long): Unit = {
        println("by name:" + x)
        println("by name:" + x)
    }

    callByValue(System.nanoTime())
    callByName(System.nanoTime()) // nanoTime evaluated twice

    def infinite(): Int = 1 + infinite()
    def printFirst(x: Int, y: => Int): Unit = println(x)

    // printFirst(infinite, 3)  // stackoverflow
    printFirst(3, infinite)
}