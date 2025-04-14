package com.example.moodmirror

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.moodmirror.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private var firstRun: Boolean = true

    // Mood data model moved to companion object for better organization
    companion object {
        val moodData = mapOf(
            "happy" to MoodResources(
                colorRes = R.color.mood_happy,
                quote = "Happiness is a direction, not a place.",
                tip = "Do something kind today.",
                music = "🎵 Song: Happy - Pharrell Williams",
                iconRes = R.drawable.ic_happy
            ),
            "sad" to MoodResources(
                colorRes = R.color.mood_sad,
                quote = "Even the darkest night will end and the sun will rise.",
                tip = "Take a moment for yourself and breathe.",
                music = "🎵 Song: Fix You - Coldplay",
                iconRes = R.drawable.ic_sad
            ),
            "calm" to MoodResources(
                colorRes = R.color.mood_calm,
                quote = "Peace begins with a smile.",
                tip = "Write your thoughts down or take a peaceful walk.",
                music = "🎵 Song: Weightless - Marconi Union",
                iconRes = R.drawable.ic_calm
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("MoodPrefs", MODE_PRIVATE)
        firstRun = prefs.getBoolean("first_run", true)

        setupUI()
        checkFirstRun()
    }

    private fun setupUI() {
        // Add pulse animations to mood buttons
        val pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse)

        with(binding) {
            btnHappy.setOnClickListener { handleMoodSelection("happy") }
            btnHappy.startAnimation(pulseAnimation)

            btnSad.setOnClickListener { handleMoodSelection("sad") }
            btnSad.startAnimation(pulseAnimation)

            btnCalm.setOnClickListener { handleMoodSelection("calm") }
            btnCalm.startAnimation(pulseAnimation)

            // Add info button for modal view
            btnInfo.setOnClickListener { showInfoDialog() }
        }
    }

    private fun handleMoodSelection(mood: String) {
        // Save mood preference
        prefs.edit().apply {
            putString("last_mood", mood)
            putBoolean("first_run", false)
            apply()
        }

        // Create mood reflection intent with animation
        val intent = Intent(this, ReflectionActivity::class.java).apply {
            moodData[mood]?.let {
                putExtra("mood", mood)
                putExtra("colorRes", it.colorRes)
                putExtra("quote", it.quote)
                putExtra("tip", it.tip)
                putExtra("music", it.music)
            }
        }

        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun checkFirstRun() {
        if (firstRun) {
            showInfoDialog()
        }
    }

    private fun showInfoDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.app_description))
            .setPositiveButton(getString(R.string.got_it)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Don't show again") { dialog, _ ->
                prefs.edit().putBoolean("first_run", false).apply()
                dialog.dismiss()
            }
            .show()
    }

    // Data class for better organization of mood resources
    data class MoodResources(
        val colorRes: Int,
        val quote: String,
        val tip: String,
        val music: String,
        val iconRes: Int
    )
}

