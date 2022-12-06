package lectures.part2oop

object Exceptions extends App {

    // throwable classes extend the Throwable class
    // Exception and Error a throwable

    def getInt(withException: Boolean): Int = {
        if (withException) throw new RuntimeException("With Exception")
        else 0
    }

    def test(bool: Boolean) {
        val aIntVal = try {
            getInt(bool)
        } catch {
            case e: RuntimeException => {
                println("caught a runtime")
                20
            }
        } finally {
            // we always be executed
            // optional and does not impact the expression, only for side effects
            println("finally")
        }
        println(aIntVal)
    }
    test(false)
    test(true)
}