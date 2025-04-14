package com.example.moodmirror

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Enhanced data class with all mood properties
data class MoodData(
    val moodName: String,
    @StringRes val quoteRes: Int,
    @StringRes val tipRes: Int,
    val song: String,
    @ColorRes val colorRes: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val backgroundRes: Int,
    val haiku: List<String> = emptyList()
) {
    companion object {
        // Default values for unknown moods
        val DEFAULT = MoodData(
            moodName = "Reflective",
            quoteRes = R.string.default_quote,
            tipRes = R.string.default_tip,
            song = "‘River Flows in You’ by Yiruma",
            colorRes = R.color.mood_neutral,
            iconRes = R.drawable.ic_neutral,
            backgroundRes = R.drawable.bg_neutral,
            haiku = listOf(
                "Gentle thoughts arise",
                "Like leaves floating on water",
                "Peace in reflection"
            )
        )
    }
}

object MoodLibrary {
    // Cache for better performance
    private val moodCache = mutableMapOf<String, MoodData>()

    fun getMoodData(mood: String): MoodData {
        return moodCache.getOrPut(mood.lowercase()) {
            when (mood.lowercase()) {
                "happy" -> createHappyMood()
                "sad" -> createSadMood()
                "calm" -> createCalmMood()
                else -> MoodData.DEFAULT
            }
        }
    }

    private fun createHappyMood() = MoodData(
        moodName = "Happy 😊",
        quoteRes = R.string.quote_happy,
        tipRes = R.string.tip_happy,
        song = "‘Happy’ by Pharrell Williams",
        colorRes = R.color.mood_happy,
        iconRes = R.drawable.ic_happy,
        backgroundRes = R.drawable.bgmscaled,
        haiku = listOf(
            "Sunlight through the trees",
            "Laughter dances on the breeze",
            "Joy blooms endlessly"
        )
    )

    private fun createSadMood() = MoodData(
        moodName = "Sad 😢",
        quoteRes = R.string.quote_sad,
        tipRes = R.string.tip_sad,
        song = "‘Fix You’ by Coldplay",
        colorRes = R.color.mood_sad,
        iconRes = R.drawable.ic_sad,
        backgroundRes = R.drawable.bgmscaled,
        haiku = listOf(
            "Raindrops on the pane",
            "Heavy heart like stormy skies",
            "Light will come again"
        )
    )

    private fun createCalmMood() = MoodData(
        moodName = "Calm 🧘",
        quoteRes = R.string.quote_calm,
        tipRes = R.string.tip_calm,
        song = "‘Weightless’ by Marconi Union",
        colorRes = R.color.mood_calm,
        iconRes = R.drawable.ic_calm,
        backgroundRes = R.drawable.bgmscaled,
        haiku = listOf(
            "Still water mirrors",
            "Mountains standing tall and proud",
            "Peace within myself"
        )
    )

    // Extension function to get random haiku line
    fun MoodData.getRandomHaikuLine(): String = haiku.random()
}