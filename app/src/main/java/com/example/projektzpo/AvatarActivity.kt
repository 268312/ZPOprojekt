package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AvatarActivity : BaseActivity() {

    private var backButton: ImageButton? = null

    private var nameTextView: TextView? = null

    private var logOutButton: Button? = null

    private val db = Firebase.firestore

    private val dbOperations = FireStoreHandler(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_view)
        backButton = findViewById(R.id.avatarBack)
        nameTextView = findViewById(R.id.avatarNameTextView)
        logOutButton = findViewById(R.id.logoutButton)
        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        logOutButton?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            FirebaseAuth.getInstance().signOut()
            startActivity(intent)
        }


        GlobalScope.launch(Dispatchers.Main) {
            val user = FirebaseAuth.getInstance().currentUser
            val email = user?.email.toString()
            val data = dbOperations.getData(email)
            nameTextView?.text = "${data?.name.toString()}"
        }
    }
}