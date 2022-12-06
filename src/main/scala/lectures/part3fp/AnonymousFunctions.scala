package lectures.part3fp

object AnonymousFunctions extends App {

    // anonymous function: Lambda

    val doubler = (x: Int) => x * 2

    println(doubler(2))

    val superAdder = (num: Int) =>
        (x: Int) => x + num
    

    println(superAdder(3)(4))
}