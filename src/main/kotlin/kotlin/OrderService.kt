import OfferService.FruitOffer
import lib.InputReader
import lib.OutputPrinter
import kotlin.reflect.KClass

fun main(vararg args: String) {

        val inputReader = InputReader()
        val outputPrinter = OutputPrinter()
        val fruitOrder = FruitOffer()
        MailServiceChannel.subscribe{
                message: Message -> println("${message.status}: ${message.deliveryTimeMessage}")
        }
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
                MailServiceChannel.publish(Message("fruit order processed", "delivery time = 3-5 business days"))
        }
        while (true)


}

object MailServiceChannel {

        val subscribers: MutableMap<KClass<*>, (Any) -> Unit> = mutableMapOf()

        inline fun <reified T : Any> publish(event: T) {
                subscribers[T::class]?.invoke(event)
        }

        inline fun <reified T> subscribe(noinline subscriber: (T) -> Unit) {
                subscribers[T::class] = subscriber as (Any) -> Unit
        }

        // expects 2 args: <topic> and <count>
        private fun publishSuccessMessage(vararg args: String) {



        }

}

data class Message(val status: String, val deliveryTimeMessage: String)

