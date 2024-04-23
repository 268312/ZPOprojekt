package com.example.projektzpo

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.MainViewBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Przejście do AnalysisActivity po kliknięciu przycisku "analysis"
        binding.analysis.setOnClickListener {
            val intent = Intent(this, AnalysisActivity::class.java)
            startActivity(intent)
        }

        // Przejście do CalendarActivity po kliknięciu przycisku "kalendarz"
        binding.kalendarz.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        // Przejście do AvatarActivity po kliknięciu przycisku "avatarButton"
        binding.avatarButton.setOnClickListener {
            val intent = Intent(this, AvatarActivity::class.java)
            startActivity(intent)
        }
    }
}

