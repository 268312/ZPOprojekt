package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.CalendarViewBinding

open class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: CalendarViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
