package com.example.e_comm_home_demo_app.presentation.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comm_home_demo_app.databinding.FragmentOffersBinding

class OffersFragment : Fragment() {

    private lateinit var binding: FragmentOffersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOffersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}