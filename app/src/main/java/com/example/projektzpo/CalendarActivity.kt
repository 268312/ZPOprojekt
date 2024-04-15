package com.example.projektzpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.FragmentCalendarBinding

open class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calendarBack.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_CalendarFragment_to_SecondFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
