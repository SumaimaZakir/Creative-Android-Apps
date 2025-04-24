package com.example.flavorfinder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalorieCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_calculator)

        val inputIngredients = findViewById<EditText>(R.id.edit_ingredient)
        val resultText = findViewById<TextView>(R.id.txt_result)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        btnCalculate.setOnClickListener {
            val ingredients = inputIngredients.text.toString().lowercase()
            val calories = ingredients.length * 15 // Basic calorie estimation
            val dish = suggestDish(ingredients)
            resultText.text = "Estimated Calories: $calories kcal\nSuggested Dish: $dish"
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