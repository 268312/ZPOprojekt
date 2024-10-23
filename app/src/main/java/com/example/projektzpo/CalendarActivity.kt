package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import com.example.projektzpo.FireStoreHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.async


class CalendarActivity : BaseActivity() {

    private var backButton: ImageButton? = null
    private var calendarView: CalendarView? = null
    private lateinit var userEmail: String
    private val db = Firebase.firestore
    private var selectedDateTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_view)

        backButton = findViewById(R.id.calendarBack)
        calendarView = findViewById(R.id.calendarView)
        selectedDateTextView = findViewById(R.id.selectedDateTextView)

        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Format the selected date to yyyy-MM-dd
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val selectedDate = sdf.format(calendar.time)
            fetchDataFromFirestore(selectedDate)
        }

    }
    // Function to fetch data from Firestore based on document ID
    // chyba gitara!!!!!!!! dokończ
    private fun fetchDataFromFirestore(documentId: String) {
        val selectedDateTextView = findViewById<TextView>(R.id.selectedDateTextView)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            userEmail = it.email ?: ""
        }
        // Get the document from Firestore
        db.collection("measurements").document(userEmail).collection("measurement").document(documentId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                //    Log.e("FirestoreError", "widzi")
                    // Document was found, extract and display data (assuming there's a "message" field)
                    val weight = document.getDouble("weight") ?: "No message available"
                    val bloodPressure = document.getString("bloodPressure") ?: "No message available"
                    val pulse = document.getDouble("pulse") ?: "No message available"
                    val saturation = document.getDouble("saturation") ?: "No message available"
                    val glucose = document.getDouble("glucose") ?: "No message available"
                    val cholesterole = document.getDouble("cholesterole") ?: "No message available"
                    selectedDateTextView.text = "Data pomiaru: $documentId\nWaga: $weight kg \nCiśnienie krwi:  $bloodPressure mm Hg \nPuls: $pulse bpm \nSaturacja: $saturation % \nPoziom glukozy: $glucose mg/dl\nPoziom cholesterolu: $cholesterole mg/dl "
                } else {
                    // Document not found, display error message
                    Log.e("FirestoreError", "brak pomiaru")
                    selectedDateTextView.text = "Nie wykonano pomiaru dnia $documentId"
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                Log.e("FirestoreError", "Error fetching document", exception)
                selectedDateTextView.text = "Error fetching data."
            }
    }

}
