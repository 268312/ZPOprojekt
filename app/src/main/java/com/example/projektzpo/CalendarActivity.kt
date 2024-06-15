package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton

class CalendarActivity : BaseActivity() {

    private var backButton: ImageButton? = null

    private var calendarView: CalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_view)

        backButton = findViewById(R.id.calendarBack)
        calendarView = findViewById(R.id.calendarView)

        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
