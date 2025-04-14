package com.sumaima.tipofthedaycalculator


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val tips = listOf(
        "Stay hydrated throughout the day!",
        "Take short breaks while studying.",
        "Keep a daily to-do list.",
        "Sleep at least 7-8 hours.",
        "Practice gratitude daily.",
        "Declutter your workspace.",
        "Smile more often!",
        "Don't skip breakfast!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipText: TextView = findViewById(R.id.tipText)
        val getTipBtn: Button = findViewById(R.id.getTipBtn)
        val infoBtn: FloatingActionButton = findViewById(R.id.infoBtn)

        getTipBtn.setOnClickListener {
            val randomTip = tips.random()
            tipText.text = randomTip
        }

        infoBtn.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

    }
}
