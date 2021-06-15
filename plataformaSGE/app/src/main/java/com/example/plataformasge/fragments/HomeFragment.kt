package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.plataformasge.R
import com.example.plataformasge.databinding.FragmentHomeBinding
import com.example.plataformasge.databinding.FragmentPersonalDataBinding
import com.example.plataformasge.models.ViewModelHomeFragments

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelHomeFragments by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel.userEntered.observe(viewLifecycleOwner, Observer { user ->
            binding.homeWelcomeName.setText("¡Bienvenido de nuevo ${user.name}!")
        })

        return binding.root
    }

}