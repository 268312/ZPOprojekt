package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import com.example.projektzpo.databinding.CalendarViewBinding
import com.google.firebase.auth.FirebaseAuth

class CalendarActivity : BaseActivity() {

    private lateinit var binding: CalendarViewBinding

    private var backButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backButton = findViewById(R.id.calendarBack)
        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.calendarBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
