package com.example.projektzpo

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.MainViewBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.analysis.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_SecondFragment_to_AnalysisFragment)
    }
        binding.kalendarz.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_SecondFragment_to_CalendarFragment)
        }
        binding.avatarButton.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_SecondFragment_to_AvatarFragment)
        }

    }
}
