package lectures.part1basics

object ValuesVariablesType extends App {
    val x: Int = 2
    println(x)

    // error: val is immutable
    // x = 3

    val aString: String = "Hello"
    val aBoolean: Boolean = true
    val aShort: Short = 12345
    val aLong: Long = 1234567
    val aFloat: Float = 2.0f
    val aDouble: Double = 3.14

    // Variables

    var aIntVar: Int = x
    aIntVar = 4
    println(aIntVar)
}