package com.example.projektzpo

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
        binding.calendarBack.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_CalendarView_to_MainView)
        }
    }
}
