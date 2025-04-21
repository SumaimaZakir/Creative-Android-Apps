package com.example.flavorfinder

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class InfoDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("About Flavor Finder")
            .setMessage("Enter your ingredients and get a meal idea! Your flavor preferences are saved automatically.")
            .setPositiveButton("OK", null)
            .create()
    }
}
