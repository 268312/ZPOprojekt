package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

class AnalysisActivity : BaseActivity() {

    private var backButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.analysis_view)
        backButton = findViewById(R.id.analysisBack)
        backButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}