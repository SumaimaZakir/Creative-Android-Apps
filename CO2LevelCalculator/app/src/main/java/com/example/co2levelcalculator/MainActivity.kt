package com.example.co2levelcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var inputActivity: EditText
    lateinit var resultText: TextView
    lateinit var calculateButton: Button
    lateinit var infoButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputActivity = findViewById(R.id.inputActivity)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        infoButton = findViewById(R.id.infoButton)

        calculateButton.setOnClickListener {
            val activity = inputActivity.text.toString().lowercase().trim()
            val result = calculateCO2Impact(activity)
            resultText.text = result
        }

        infoButton.setOnClickListener {
            showInfoDialog()
        }
        infoButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("What’s this App?")
            builder.setMessage("This app calculates your carbon dioxide (CO₂) level based on your input. It’s a simple way to reflect on your environmental impact 🌍.\n\nUse responsibly and aim to reduce emissions for a healthier Earth!")

            builder.setPositiveButton("Got it!") { dialog, _ -> dialog.dismiss() }

            val dialog = builder.create()
            dialog.show()
        }

    }

    private fun calculateCO2Impact(activity: String): String {
        return when {
            activity.contains("drive") -> "🚗 High CO₂ output! Try walking or biking."
            activity.contains("fly") -> "✈️ Extremely high CO₂. Maybe try a train?"
            activity.contains("meat") -> "🥩 CO₂-intensive diet. Go green once a week!"
            activity.contains("bike") || activity.contains("walk") -> "🌿 Low CO₂ impact. Well done!"
            activity.contains("plant") -> "🌱 Negative CO₂! You're helping the planet."
            else -> "🤔 Activity unknown. Try something like 'driving', 'eating meat', or 'cycling'."
        }
    }

    private fun showInfoDialog() {
        val message = "This creative calculator gives a fun CO₂ rating based on your activities. Just type something like 'driving', 'flying', or 'cycling' and press Calculate."
        AlertDialog.Builder(this)
            .setTitle("About This App")
            .setMessage(message)
            .setPositiveButton("Got it!") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
