package com.example.flavorfinder

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputIngredients: EditText
    private lateinit var resultText: TextView
    private lateinit var generateButton: Button
    private lateinit var spiceSpinner: Spinner
    private lateinit var dietSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect layout views
        inputIngredients = findViewById(R.id.inputIngredients)
        resultText = findViewById(R.id.resultText)
        generateButton = findViewById(R.id.generateButton)
        spiceSpinner = findViewById(R.id.spiceSpinner)
        dietSpinner = findViewById(R.id.dietSpinner)

        // Populate spinners
        ArrayAdapter.createFromResource(
            this,
            R.array.spice_levels,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spiceSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.diet_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dietSpinner.adapter = adapter
        }

        // On button click
        generateButton.setOnClickListener {
            val input = inputIngredients.text.toString()
            val spice = spiceSpinner.selectedItem.toString()
            val diet = dietSpinner.selectedItem.toString()
            resultText.text = generateSuggestion(input, spice, diet)
        }
    }

    private fun generateSuggestion(input: String, spice: String, diet: String): String {
        if (input.isBlank()) return "Please enter at least one ingredient."

        val ingredients = input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val base = ingredients.randomOrNull() ?: return "Try a different ingredient list!"

        return "Try a $spice spiced $base bowl (${diet} style)!"
    }
}
