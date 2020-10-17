package OfferService

import java.math.BigDecimal

class FruitOffer {

    // using Big Decimal to avoid floating point rounding errors
    private val fruitDictionary = mapOf("Apple" to .60.toBigDecimal(), "Orange" to .25.toBigDecimal())

    fun addFruits(fruits: List<String>): BigDecimal {

        // binary switch for buy one get one free
        var appleFree = false
        var orangeFree = false
        // keep track of number of oranges so you can get the third free
        var numberOfOrangesSinceLastFree = 0

        return fruits.map{
            if (it == "Apple") {
                if (appleFree) {
                    appleFree = false
                    BigDecimal.ZERO
                } else {
                    appleFree = true
                    fruitDictionary[it]
                }
            } else if (it == "Orange") {
                // if we are about to buy a 3rd orange, set the boolean to free
                if (numberOfOrangesSinceLastFree == 2) {
                    orangeFree = true
                }
                if (orangeFree) {
                    orangeFree = false
                    numberOfOrangesSinceLastFree = 0
                    BigDecimal.ZERO
                } else {
                    numberOfOrangesSinceLastFree++
                    fruitDictionary[it]
                }
            } else {
                BigDecimal.ZERO
            }

        }.sumByBigDecimal { it ?: BigDecimal.ZERO }
    }

    // custom inline function for summing over collection using Big Decimal
    inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
        var sum: BigDecimal = BigDecimal.ZERO
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }


}