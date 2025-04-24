package com.example.bmi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calculate BMI Button
        binding.calculateButton.setOnClickListener {
            val heightText = binding.heightInput.text.toString()
            val weightText = binding.weightInput.text.toString()

            if (heightText.isNotEmpty() && weightText.isNotEmpty()) {
                val height = heightText.toFloat()
                val weight = weightText.toFloat()
                val bmi = weight / ((height / 100) * (height / 100))

                // Navigate to ResultActivity with BMI value
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("BMI_RESULT", bmi)
                startActivity(intent)
            } else {
                binding.resultText.text = "Please enter both height and weight."
            }
        }
        val selectedGenderId = binding.genderGroup.checkedRadioButtonId
        val gender = when (selectedGenderId) {
            R.id.maleRadio -> "Male"
            R.id.femaleRadio -> "Female"
            else -> "Not selected"
        }


        // Show Info Popup
        binding.infoButton.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.info_dialog, null)

            AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}



