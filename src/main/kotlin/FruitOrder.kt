class FruitOrder {

    val fruitDictionary = mapOf("Apple" to .60, "Orange" to .25)

    fun addFruits(fruits: List<String>): Double {

       return fruits.map { fruitDictionary[it] }.sumByDouble { it ?: 0.0 }

    }

}