package lectures.part1basics

object Expressions extends App {
    val x = 1 + 2
    println(x)

    println(2 + 3)

    println(x == 2)

    // Instructions (DO something) vs Expressions (return something - VALUE)

    // IF expression

    val aCondition = true
    val aConditionValue = if (aCondition) 3 else 5
    println(aConditionValue)

    // side effects: println(), while, reassigning => return Unit (equivalent to void/None)

    val aCodeBlock = {
        val y = 2
        val z = y + 1

        if (z > 2) "hello" else "good bye"
    }
    println(aCodeBlock)
}