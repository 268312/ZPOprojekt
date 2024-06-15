package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private var welcomeTextView: TextView? = null

    private var calendarButton: Button? = null

    private var measurementButton: Button? = null

    private var analysisButton: Button? = null

    private var avatarButton: FloatingActionButton? = null

    private val db = Firebase.firestore

    private val dbOperations = FireStoreHandler(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
        welcomeTextView = findViewById(R.id.welcomeText)
        calendarButton = findViewById(R.id.kalendarz)
        measurementButton = findViewById(R.id.addMeasurement)
        analysisButton = findViewById(R.id.analysis)
        avatarButton = findViewById(R.id.avatarButton)
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dbOperations.getData(email)
            welcomeTextView?.text = "Witaj ${data?.name.toString()}!"
        }

        calendarButton?.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        measurementButton?.setOnClickListener {
            val intent = Intent(this, MeasurementActivity::class.java)
            startActivity(intent)
        }

        analysisButton?.setOnClickListener {
            val intent = Intent(this, AnalysisActivity::class.java)
            startActivity(intent)
        }

        avatarButton?.setOnClickListener {
            val intent = Intent(this, AvatarActivity::class.java)
            startActivity(intent)
        }
    }
}

