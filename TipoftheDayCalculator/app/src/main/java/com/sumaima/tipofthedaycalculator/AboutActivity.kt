package com.sumaima.tipofthedaycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val closeBtn: Button = findViewById(R.id.closeBtn)
        closeBtn.setOnClickListener {
            finish()
        }
    }
}
