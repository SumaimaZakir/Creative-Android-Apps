package com.example.moodmirror

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.moodmirror.databinding.ActivityReflectionBinding
import com.google.android.material.card.MaterialCardView

class ReflectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReflectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReflectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get mood data from intent with default values
        val mood = intent.getStringExtra("mood") ?: "neutral"
        val quote = intent.getStringExtra("quote") ?: getString(R.string.default_quote)
        val tip = intent.getStringExtra("tip") ?: getString(R.string.default_tip)
        val music = intent.getStringExtra("music") ?: getString(R.string.default_music)
        val colorRes = intent.getIntExtra("colorRes", R.color.mood_neutral)

        setupUI(mood, quote, tip, music, colorRes)
        setupListeners()
    }

    private fun setupUI(mood: String, quote: String, tip: String, music: String, colorRes: Int) {
        // Set mood-themed colors
        val moodColor = ContextCompat.getColor(this, colorRes)
        val mutedColor = adjustAlpha(moodColor, 0.2f)

        with(binding) {
            // Apply mood color scheme
            root.setBackgroundColor(mutedColor)
            moodCard.strokeColor = moodColor
            moodCard.setCardBackgroundColor(
                ColorStateList.valueOf(
                    adjustAlpha(moodColor, 0.05f)
                )
            )

            // Set content with formatted text
            txtMood.text = getString(R.string.mood_title, mood.capitalize())
            txtQuote.text = quote
            txtTip.text = getString(R.string.tip_prefix, tip)
            txtMusic.text = getString(R.string.music_prefix, music)

            // Set mood icon based on current mood
            imgMoodIcon.setImageResource(
                when (mood.lowercase()) {
                    "happy" -> R.drawable.ic_happy
                    "sad" -> R.drawable.ic_sad
                    "calm" -> R.drawable.ic_calm
                    else -> R.drawable.ic_neutral
                }
            )

            imgMoodIcon.imageTintList = ColorStateList.valueOf(moodColor)
        }
    }

    private fun setupListeners() {
        binding.btnSaveReflection.setOnClickListener {
            // Save reflection to preferences/database
            showConfirmation("Reflection saved!")
        }

        binding.btnShare.setOnClickListener {
            shareReflection()
        }
    }

    private fun shareReflection() {
        val shareText = """
            My Mood Reflection:
            ${binding.txtMood.text}
            ${binding.txtQuote.text}
            ${binding.txtTip.text}
            ${binding.txtMusic.text}
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(shareIntent, "Share your reflection"))
    }

    private fun showConfirmation(message: String) {
        android.app.AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = (android.graphics.Color.alpha(color) * factor).toInt()
        val red = android.graphics.Color.red(color)
        val green = android.graphics.Color.green(color)
        val blue = android.graphics.Color.blue(color)
        return android.graphics.Color.argb(alpha, red, green, blue)
    }
}
