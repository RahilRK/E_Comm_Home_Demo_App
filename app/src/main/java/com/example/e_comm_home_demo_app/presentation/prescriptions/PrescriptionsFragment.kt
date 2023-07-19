package com.example.e_comm_home_demo_app.presentation.prescriptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comm_home_demo_app.databinding.FragmentPrescriptionsBinding
import kotlinx.coroutines.*

class PrescriptionsFragment : Fragment() {

    private lateinit var binding: FragmentPrescriptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPrescriptionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}