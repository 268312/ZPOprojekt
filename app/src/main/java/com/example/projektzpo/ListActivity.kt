package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListActivity : BaseActivity() {

    private var backButton: ImageButton? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ListAdapter
    private lateinit var userEmail: String
    private var measurements: MutableList<ListElement> = mutableListOf()
    private val db = Firebase.firestore
    private val dbOperations = FireStoreHandler(db)
    private var measurementList: MutableList<Pair<String, Measurement>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)
        backButton = findViewById(R.id.listBack)
        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        recyclerView = findViewById(R.id.listRecyclerView)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            userEmail = it.email ?: ""
        }

        GlobalScope.launch(Dispatchers.Main) {
            val deferred = async {
                dbOperations.getMeasurement(userEmail)
            }
            measurementList = deferred.await()

            for ((id, measurement) in measurementList) {
                val element = ListElement(id,
                    measurement.weight,
                    measurement.bloodPressure,
                    measurement.pulse,
                    measurement.saturation,
                    measurement.glucose,
                    measurement.cholesterole)
                measurements.add(element)
            }

            itemAdapter.notifyDataSetChanged()
        }

        itemAdapter = ListAdapter(measurements)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}