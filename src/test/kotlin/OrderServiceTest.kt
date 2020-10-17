import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    // The main logic that we need to test (given that we are reading from standard in
    // correctly is that the summation of the fruits works given a valid series of fruits
    @Nested
    inner class FruitSummation {

        @Test
        fun `one orange`() {
            val fruits = listOf<String>("Orange")
            val fruitOrder = FruitOrder()
            val expectedResult = 0.25
            assertEquals(expectedResult,fruitOrder.addFruits(fruits))
        }

        @Test
        fun `one apple`() {
            val fruits = listOf<String>("Apple")
            val fruitOrder = FruitOrder()
            val expectedResult = 0.60
            assertEquals(expectedResult,fruitOrder.addFruits(fruits))

        }

        @Test
        fun `random selection of oranges and apples`() {
            val fruits = listOf<String>("Orange", "Apple", "Apple", "Apple")
            val fruitOrder = FruitOrder()
            val expectedResult = 2.05
            assertEquals(expectedResult,fruitOrder.addFruits(fruits))

        }

        @Test
        fun `no oranges or apples`() {
            val fruits = listOf<String>()
            val fruitOrder = FruitOrder()
            val expectedResult = 0.0
            assertEquals(expectedResult,fruitOrder.addFruits(fruits))

        }



    }
}