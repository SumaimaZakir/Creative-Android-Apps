package com.example.flavorfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenCalculator = findViewById<Button>(R.id.btn_open_calculator)
        btnOpenCalculator.setOnClickListener {
            val intent = Intent(this, CalorieCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}



