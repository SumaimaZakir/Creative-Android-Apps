package com.example.bmi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bmiResult = intent.getFloatExtra("BMI_RESULT", 0f)
        val gender = intent.getStringExtra("GENDER") ?: "Not Specified"

        val resultText = findViewById<TextView>(R.id.resultText)
        val infoText = findViewById<TextView>(R.id.infoText)

        resultText.text = String.format("Your BMI is: %.2f", bmiResult)
        infoText.text = "Gender: $gender"
    }
}

