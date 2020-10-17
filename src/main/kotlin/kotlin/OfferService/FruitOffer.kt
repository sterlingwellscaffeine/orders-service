package OfferService

import java.math.BigDecimal

class FruitOffer {

    // using Big Decimal to avoid floating point rounding errors
    private val fruitDictionary = mapOf("Apple" to .60.toBigDecimal(), "Orange" to .25.toBigDecimal())

    private val numOranges = 5
    private val numApples = 5

    fun addFruits(fruits: List<String>): BigDecimal {

        // binary switch for buy one get one free
        var appleFree = false
        var orangeFree = false
        // keep track of number of oranges so you can get the third free
        var numberOfOrangesSinceLastFree = 0

        // keeping track of number of apples and oranges so we can send failed messages to the pub sub
        var numberOfApplesInOrder = 0
        var numberOfOrangesInOrder = 0
        var sum = BigDecimal.ZERO;

        for (elem in fruits) {
            if (elem == "Apple") {
                numberOfApplesInOrder += 1
                if (numberOfApplesInOrder > numApples) {
                    return (-1).toBigDecimal() // return error signal
                }
                if (appleFree) {
                    // if the apple is free, don't increment sum and put the counter back to appleFalse
                    appleFree = false;
                } else {
                    // else increment the sum
                    sum += fruitDictionary.get(elem) ?: BigDecimal.ZERO
                    appleFree = true
                }
            } else if (elem == "Orange") {
                numberOfOrangesInOrder += 1
                if (numberOfOrangesInOrder > numOranges) {
                    return (-1).toBigDecimal() // return error signal
                }
                if (numberOfOrangesSinceLastFree == 2) {
                    orangeFree = true
                }
                if (orangeFree) {
                    // if the apple is free, don't increment sum and put the counter back to appleFalse
                    orangeFree = false;
                    numberOfOrangesSinceLastFree = 0;
                } else {
                    // else increment the sum and # of oranges since last free orange
                    sum += fruitDictionary.get(elem) ?: BigDecimal.ZERO
                    numberOfOrangesSinceLastFree += 1
                }
            }
        }
        return sum
    }
}