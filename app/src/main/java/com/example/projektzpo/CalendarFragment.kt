package com.example.projektzpo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.projektzpo.databinding.FragmentCalendarBinding
import com.example.projektzpo.databinding.MainViewBinding

open class CalendarActivity : AppCompatActivity() {
    private var _binding: FragmentCalendarBinding? = null

    
    private val binding get() = _binding!!
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarBack.setOnClickListener{
            findNavController().navigate(R.id.action_CalendarFragment_to_SecondFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
