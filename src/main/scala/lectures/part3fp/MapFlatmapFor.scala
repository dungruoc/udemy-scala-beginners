package lectures.part3fp

object MapFlatmapFor extends App {

    def combinations[A, B, C](x: List[A], y: List[B], comb: (A, B) => C): List[C] = {
        x.flatMap((x: A) => {
            y.map((y: B) => comb(x, y))
        })
    }

    println(combinations(List(1, 2, 3, 4), List("a", "b", "c"),
        (x: Int, y: String) => s"$y:$x"
    ))

    // for-comprehention
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c')
    val colors = List("red", "green", "blue")

    val colorCombinations = for {
        num <- numbers if num % 2 == 0
        char <- chars
        color <- colors
    } yield "" + char + num + "_" + color

    println(colorCombinations)
}