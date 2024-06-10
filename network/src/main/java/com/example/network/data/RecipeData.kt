package com.example.network.data

data class RecipeData(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Any,
    val creditsText: String,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val lowFodmap: Boolean,
    val nutrition: Nutrition,
    val occasions: List<String>,
    val originalId: Any,
    val preparationMinutes: Any,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
) {
    data class AnalyzedInstruction(
        val name: String,
        val steps: List<Step>
    ) {
        data class Step(
            val equipment: List<Equipment>,
            val ingredients: List<Any>,
            val number: Int,
            val step: String
        ) {
            data class Equipment(
                val id: Int,
                val image: String,
                val localizedName: String,
                val name: String
            )
        }
    }

    data class ExtendedIngredient(
        val aisle: String,
        val amount: Double,
        val consistency: String,
        val id: Int,
        val image: String,
        val measures: Measures,
        val meta: List<String>,
        val name: String,
        val nameClean: String,
        val original: String,
        val originalName: String,
        val unit: String
    ) {
        data class Measures(
            val metric: Metric,
            val us: Us
        ) {
            data class Metric(
                val amount: Double,
                val unitLong: String,
                val unitShort: String
            )

            data class Us(
                val amount: Double,
                val unitLong: String,
                val unitShort: String
            )
        }
    }

    data class Nutrition(
        val caloricBreakdown: CaloricBreakdown,
        val flavonoids: List<Flavonoid>,
        val ingredients: List<Ingredient>,
        val nutrients: List<Nutrient>,
        val properties: List<Property>,
        val weightPerServing: WeightPerServing
    ) {
        data class CaloricBreakdown(
            val percentCarbs: Double,
            val percentFat: Double,
            val percentProtein: Double
        )

        data class Flavonoid(
            val amount: Double,
            val name: String,
            val unit: String
        )

        data class Ingredient(
            val amount: Double,
            val id: Int,
            val name: String,
            val nutrients: List<Nutrient>,
            val unit: String
        ) {
            data class Nutrient(
                val amount: Double,
                val name: String,
                val percentOfDailyNeeds: Double,
                val unit: String
            )
        }

        data class Nutrient(
            val amount: Double,
            val name: String,
            val percentOfDailyNeeds: Double,
            val unit: String
        )

        data class Property(
            val amount: Double,
            val name: String,
            val unit: String
        )

        data class WeightPerServing(
            val amount: Int,
            val unit: String
        )
    }
}