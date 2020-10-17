import OfferService.FruitOffer
import lib.InputReader
import lib.OutputPrinter

fun main(vararg args: String) {

        val inputReader = InputReader()
        val outputPrinter = OutputPrinter()
        val fruitOrder = FruitOffer()

        do {
                // read in all of the fruits and add them to the fruit array
                val line = inputReader.readLn().replace("\\s+".toRegex(), "").replace("[", "")
                        .replace("]", "")
                // if you want to exit the order service
                if (line == "quit") {
                        println("Closing Program")
                        break
                }

                val fruits = line.split(",")
                // add up all of the fruits based on their mapping
                val sum = fruitOrder.addFruits(fruits)
                outputPrinter.printline("\$$sum")
        }
        while (true)
}

