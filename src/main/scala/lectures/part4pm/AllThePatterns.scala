package lectures.part4pm

object AllThePatterns extends App {

    // 1. Constants

    def match_constants(const: Any): String = const match {
        case 1 => "One"
        case true => "True"
        case "Scala" => "Scala"
        case AllThePatterns => "AllThePatterns"
    }

    println(match_constants(AllThePatterns))

    // 2. tuples

    val aTuple = (1, (2, 3))
    val aMatchTuple = aTuple match {
        case (2, _) => println("2, _")
        case (_, (something, 3)) => println(s"$something found with 3 in the second element")
    }

    // 3. case classes

    // 4. list patterns

    val aList = List(1, 2, 3, 4, 5)
    val aListMatch = aList match {
        case List(1, _, _, _, _) => ???
        case List(1, _*) => ???
        case 1 :: List(_) => ???
        case List(1, 2, 3, 4) :+ 5 
    }

    // 5. type specifiers

    val unknown: Any = 2

    val unknownMatch = unknown match {
        case list: List[Int] => ???
    }
}