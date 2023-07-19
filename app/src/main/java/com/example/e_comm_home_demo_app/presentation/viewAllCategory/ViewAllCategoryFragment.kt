package com.example.e_comm_home_demo_app.presentation.viewAllCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comm_home_demo_app.databinding.FragmentViewallCategoryBinding

class ViewAllCategoryFragment : Fragment() {

    private lateinit var binding: FragmentViewallCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewallCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}