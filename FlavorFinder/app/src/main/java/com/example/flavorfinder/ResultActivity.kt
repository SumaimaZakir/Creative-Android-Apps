package com.example.flavorfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultText = findViewById<TextView>(R.id.txt_all_results)
        val btnClear = findViewById<Button>(R.id.btn_clear_all)
        val btnBack = findViewById<Button>(R.id.btn_back)

        val db = IngredientDatabase.getDatabase(this)
        val dao = db.ingredientDao()

        dao.getAllIngredients().observe(this, Observer { ingredients ->
            val allText = if (ingredients.isNotEmpty()) {
                ingredients.joinToString("\n") { "${it.name} → ${it.calories} kcal" }
            } else {
                "No ingredients saved yet."
            }
            resultText.text = allText
        })

        btnClear.setOnClickListener {
            lifecycleScope.launch {
                dao.clearAll()
            }
        }

        btnBack.setOnClickListener {
            finish() // or use startActivity(Intent(this, CalorieCalculatorActivity::class.java))
        }
    }
}
