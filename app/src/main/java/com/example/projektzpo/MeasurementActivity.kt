package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.Timestamp

class MeasurementActivity : BaseActivity() {

    private lateinit var saveButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: MeasurementAdapter
    private lateinit var userEmail: String
    private lateinit var measurements: MutableList<Measurement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.measurement_view)

        backButton = findViewById(R.id.measurementBack)
        recyclerView = findViewById(R.id.recyclerView)
        saveButton = findViewById(R.id.save)

        // Uzyskanie emaila zalogowanego użytkownika
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            userEmail = it.email ?: ""
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        measurements = mutableListOf(
        //           Measurement(userEmail, Timestamp.now(), null, "", null, null, null, null)
        )

        itemAdapter = MeasurementAdapter(measurements)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        saveButton.setOnClickListener {
            saveDataToFirestore()
        }
    }

    private fun saveDataToFirestore() {
        val db = Firebase.firestore
        val measurements = itemAdapter.measurements

        measurements.forEach { measurement ->
            // Ustawienie aktualnego czasu
            measurement.time = Timestamp.now()

            db.collection("measurements").add(measurement)
                .addOnSuccessListener { documentReference ->
                    Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding document", e)
                }
        }
    }
}