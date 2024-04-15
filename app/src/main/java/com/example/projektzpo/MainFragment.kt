package com.example.projektzpo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projektzpo.databinding.MainViewBinding


/**
 * Zamienić na activity
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MainFragment : Fragment() {

    private var _binding: MainViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {

        _binding = MainViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.analysis.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_AnalysisFragment)
    }
        binding.kalendarz.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_CalendarFragment)
        }
        binding.avatarButton.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_AvatarFragment)
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}