package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AvatarActivity : AppCompatActivity() {

    private var backButton: ImageButton? = null
    private var nameTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_view)
        backButton = findViewById(R.id.avatarBack)
        nameTextView = findViewById(R.id.avatarNameTextView)
        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        nameTextView
    }
}