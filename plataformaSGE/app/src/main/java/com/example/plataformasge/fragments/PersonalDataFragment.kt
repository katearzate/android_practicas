package com.example.plataformasge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.plataformasge.R
import com.example.plataformasge.databinding.FragmentPersonalDataBinding
import com.example.plataformasge.models.ViewModelHomeFragments

class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelHomeFragments by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalDataBinding.inflate(layoutInflater)

        viewModel.userEntered.observe(viewLifecycleOwner, Observer { user ->
            binding.personalDataNoControl.setText(user.noControl)
            binding.personalDataStudentName.setText(user.name)
            binding.personalDataStudentLastnames.setText(user.lastNames)
            binding.personalDataCareer.setText(user.career)
            binding.personalDataSemester.setText(user.semester)
            binding.personalDataPassword.setText(user.password)
            println("USUARIO DESDE FRAGMENTO: "+user)
        })
        //TOMAR LOS VALORES MANDADOS AL HACER LOGIN ************************+


        binding.personalDataBtnUpdate.setOnClickListener {
            //llamar db para actualizar
        }

        return binding.root
    }

}