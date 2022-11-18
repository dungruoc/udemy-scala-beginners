package lectures.part2oop

object OOBasics extends App {

    val aWriter = new Writer("Patrick", "Mondiano", 1954)

    println(aWriter.fullName)

    val aNovel = new Novel("Rue des ", 1994, aWriter)

    println(aNovel.authorAge)
    println(aNovel.isWrittenBy(aWriter))

    val aCopy = aNovel.copy(2000)

    println(aCopy.authorAge)
    println(aCopy.isWrittenBy(aWriter))

    val aCounter = new Counter(5)
    println(aCounter.currentCount)
    println(aCounter.increment.currentCount)
    println(aCounter.increment(3).currentCount)
    println(aCounter.decrement.currentCount)
    println(aCounter.decrement(4).currentCount)

}

class Writer(firstName: String, surName: String, val birthYear: Int) {

    def fullName(): String = {
        s"$firstName $surName"
    }
}

class Novel(name: String, releaseYear: Int, author: Writer) {
    def authorAge(): Int = {
        releaseYear - author.birthYear
    }

    def isWrittenBy(writer: Writer): Boolean = {
        writer.fullName == author.fullName
    }

    def copy(releaseYear: Int): Novel = {
        new Novel(name, releaseYear, author)
    }
}

class Counter(count: Int) {
    def currentCount(): Int = count

    def increment(): Counter = {
        new Counter(count + 1)
    }

    def increment(amount: Int): Counter = {
        new Counter(count + amount)
    }

    def decrement(): Counter = {
        new Counter(count - 1)
    }

    def decrement(amount: Int): Counter = {
        new Counter(count - amount)
    }
}