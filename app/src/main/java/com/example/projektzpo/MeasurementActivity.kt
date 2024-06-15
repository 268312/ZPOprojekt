package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MeasurementActivity: BaseActivity() {

    private var backButton: ImageButton? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.measurement_view)

        backButton = findViewById(R.id.measurementBack)
        recyclerView = findViewById(R.id.recyclerView)

        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val measurements = mutableListOf(
            Measurement("Data pomiaru"),
            Measurement("Waga"),
            Measurement("Ciśnienie krwi"),
            Measurement("Tętno"),
            Measurement("Saturacja pO2"),
            Measurement("Poziom glukozy"),
            Measurement("Poziom cholesterolu")
        )

        val adapter = MeasurementAdapter(measurements)
        recyclerView?.adapter = adapter


        recyclerView?.layoutManager = LinearLayoutManager(this)
    }
}