import OfferService.FruitOffer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.*
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    // The main logic that we need to test (given that we are reading from standard in
    // correctly is that the summation of the fruits works given a valid series of fruits
    @Nested
    inner class FruitSummation {

        @Test
        fun `one orange`() {
            val fruits = listOf<String>("Orange")
            val fruitOrder = FruitOffer()
            val expectedResult = 0.25.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `one apple`() {
            val fruits = listOf<String>("Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 0.60.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))

        }

        @Test
        fun `one apple one orange`() {
            val fruits = listOf<String>("Orange", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = .85.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))

        }

        @Test
        fun `no oranges or apples`() {
            val fruits = listOf<String>()
            val fruitOrder = FruitOffer()
            val expectedResult = BigDecimal.ZERO
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))

        }
    }
    // main logic that we are testing is the buy one get one free for apples
    // and the buy 2 get one free for oranges
    // In addition, we are testing the condition of running out of fruit as well
    @Nested
    inner class FruitOffers() {

        @Test
        fun `buy one get one free apples`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 0.85.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }


        @Test
        fun `buy 2 get one free oranges`() {
            val fruits = listOf<String>("Orange", "Orange", "Orange", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 1.10.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `buy one get one free apples and orange condition`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Orange", "Orange")
            val fruitOrder = FruitOffer()
            val expectedResult = 1.1.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `buy one get one free apples and orange condition multiple (order failed condition)`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange")
            val fruitOrder = FruitOffer()
            val expectedResult = (-1).toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `buy one get one free apples and orange condition multiple (order = condition)`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple", "Orange")
            val fruitOrder = FruitOffer()
            val expectedResult = 2.2.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `buy one get one free apples and orange condition multiple (order = apple condition)`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 2.55.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `buy one get one free apples and orange condition multiple (order failed apple condition)`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = (-1).toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }



        @Test
        fun `buy one get one free apples odd parity`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 1.45.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

        @Test
        fun `Oranges not multiple of 3`() {
            val fruits = listOf<String>("Orange", "Orange", "Apple", "Apple", "Apple")
            val fruitOrder = FruitOffer()
            val expectedResult = 1.70.toBigDecimal()
            assertThat(expectedResult, Matchers.comparesEqualTo(fruitOrder.addFruits(fruits)))
        }

    }
}