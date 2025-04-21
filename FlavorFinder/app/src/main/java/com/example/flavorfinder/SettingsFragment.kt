package com.example.flavorfinder

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Loads preferences from res/xml/preferences.xml
        setPreferencesFromResource(R.xml.preference, rootKey)
    }
}

