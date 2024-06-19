package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MeasurementActivity : BaseActivity() {

    private lateinit var saveButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: MeasurementAdapter
    private lateinit var userEmail: String
    private lateinit var measurements: MutableList<Measurement>
    private val db = Firebase.firestore
    private val dbOperations = FireStoreHandler(db)

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
            goToMainActivity()
        }

        measurements = mutableListOf(
                   Measurement(null, null, null, null, null, null)
        )

        itemAdapter = MeasurementAdapter(measurements)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        saveButton.setOnClickListener {
            saveDataToFirestore()
        }
    }

    private fun saveDataToFirestore() {
        val measurements = itemAdapter.measurements
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        measurements.forEach { measurement ->
            // Ustawienie aktualnego czasu
            if (validateMeasurementDetails(measurements)) {
                val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val data = Measurement(measurement.weight,
                    measurement.bloodPressure,
                    measurement.pulse,
                    measurement.saturation,
                    measurement.glucose,
                    measurement.cholesterole)
                GlobalScope.launch(Dispatchers.Main) {
                    val result = dbOperations.addMeasurement(email, date, data)
                    if (result.isSuccess) {
                        showErrorSnackBar("Measurement added successfully", false)
                        goToMainActivity()
                    } else {
                        showErrorSnackBar("Failed to add measurement: ${result.exceptionOrNull()?.message}", true)
                    }
                }
            }
        }
    }

    private fun validateMeasurementDetails(measurements: List<Measurement>): Boolean {
        for (measurement in measurements) {
            if (measurement.weight == null &&
                measurement.bloodPressure.isNullOrBlank() &&
                measurement.pulse == null &&
                measurement.saturation == null &&
                measurement.glucose == null &&
                measurement.cholesterole == null) {
                showErrorSnackBar("At least one measurement must be filled in", true)
                return false
            }
            // Validate weight (assuming weight must be positive)
            if (measurement.weight != null) {
                val weight = measurement.weight ?: 0.0
                if (weight <= 0) {
                    showErrorSnackBar("Invalid weight: ${measurement.weight}", true)
                    return false
                }
            }

            // Validate blood pressure (basic format check)
            val bloodPressure = measurement.bloodPressure
            if (bloodPressure != null) {
                if (!Regex("\\d{2,3}/\\d{2,3}").matches(bloodPressure)) {
                    showErrorSnackBar("Invalid blood pressure: ${measurement.bloodPressure}", true)
                    return false
                }
            }

            // Validate pulse (must be positive)
            val pulse = measurement.pulse
            if (pulse != null) {
                if (pulse <= 0) {
                    showErrorSnackBar("Invalid pulse: ${measurement.pulse}", true)
                    return false
                }
            }

            // Validate saturation (0-100%)
            val saturation = measurement.saturation
            if (saturation != null) {
                if (saturation !in 0..100) {
                    showErrorSnackBar("Invalid saturation: ${measurement.saturation}", true)
                    return false
                }
            }

            // Validate glucose (assuming it must be positive)
            val glucose = measurement.glucose
            if (glucose != null) {
                if (glucose <= 0) {
                    showErrorSnackBar("Invalid glucose: ${measurement.glucose}", true)
                    return false
                }
            }

            // Validate cholesterol (assuming it must be positive)
            val cholesterole = measurement.cholesterole
            if (cholesterole != null) {
                if (cholesterole <= 0) {
                    showErrorSnackBar("Invalid cholesterol: ${measurement.cholesterole}", true)
                    return false
                }
            }
        }
        // If all validations pass
        return true
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}