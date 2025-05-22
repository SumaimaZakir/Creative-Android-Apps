package com.example.flavorfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CalorieCalculatorActivity : AppCompatActivity() {

    private lateinit var ingredientDao: IngredientDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_calculator)

        // Initialize Room database and DAO
        val db = IngredientDatabase.getDatabase(this)
        ingredientDao = db.ingredientDao()

        val inputIngredients = findViewById<EditText>(R.id.edit_ingredient)
        val resultText = findViewById<TextView>(R.id.txt_result)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val btnViewResults = findViewById<Button>(R.id.btn_view_results)

        // Navigate to results screen
        btnViewResults.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }

        // Handle calculation and storage
        btnCalculate.setOnClickListener {
            val ingredientsInput = inputIngredients.text.toString().lowercase()
            val ingredientsList = ingredientsInput
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            if (ingredientsList.isEmpty()) {
                resultText.text = "Please enter some ingredients."
                return@setOnClickListener
            }

            val totalCalories = ingredientsInput.length * 15
            val caloriesPerIngredient = totalCalories / ingredientsList.size
            val dish = suggestDish(ingredientsInput)

            resultText.text = "Estimated Calories: $totalCalories kcal\nSuggested Dish: $dish"

            // Save each ingredient in Room
            lifecycleScope.launch {
                for (ingredient in ingredientsList) {
                    ingredientDao.insert(Ingredient(name = ingredient, calories = caloriesPerIngredient))
                }
            }
        }
    }

    private fun suggestDish(ingredients: String): String {
        return when {
            ingredients.contains("chicken") && ingredients.contains("rice") -> "Chicken Fried Rice"
            ingredients.contains("potato") && ingredients.contains("cheese") -> "Cheesy Potato Bake"
            ingredients.contains("tomato") && ingredients.contains("pasta") -> "Tomato Pasta"
            else -> "Mixed Dish Surprise"
        }
    }
}

