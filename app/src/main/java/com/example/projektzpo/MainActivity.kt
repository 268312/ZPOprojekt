package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.example.projektzpo.databinding.MainViewBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainViewBinding

    private var welcomeTextView: TextView? = null

    private val db = Firebase.firestore

    private val dbOperations = FireStoreHandler(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        welcomeTextView = findViewById(R.id.welcomeText)

        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dbOperations.getData(email)
            welcomeTextView?.text = "Witaj ${data?.name.toString()}!"
        }

        // Przejście do AnalysisActivity po kliknięciu przycisku "analysis"
        binding.analysis.setOnClickListener {
            val intent = Intent(this, AnalysisActivity::class.java)
            startActivity(intent)
        }

        // Przejście do CalendarActivity po kliknięciu przycisku "kalendarz"
        binding.kalendarz.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        // Przejście do AvatarActivity po kliknięciu przycisku "avatarButton"
        binding.avatarButton.setOnClickListener {
            val intent = Intent(this, AvatarActivity::class.java)
            startActivity(intent)
        }
    }
}

