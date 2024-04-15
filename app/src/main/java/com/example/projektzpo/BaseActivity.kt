package com.example.projektzpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat

import com.example.projektzpo.databinding.MainViewBinding

open class BaseActivity : AppCompatActivity() {
// klasa Activity, po której mogą dziedziczyć inne Activities w których korzystamy ze Snackbar
private lateinit var binding: MainViewBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainViewBinding.inflate(layoutInflater)
    setContentView(binding.root)
}

fun showErrorSnackBar(message: String, errorMessage: Boolean) {
    // Tworzenie Snackbar z przekazaną wiadomością
    val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
    val snackbarView = snackbar.view

    // Ustawienie koloru tła Snackbar w zależności od rodzaju komunikatu
    if (errorMessage) {
        snackbarView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorSnackBarError
            )
        )
    } else {
        snackbarView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorSnackBarSuccess
            )
        )
    }
    // Wyświetlenie Snackbar
    snackbar.show()
}
}
