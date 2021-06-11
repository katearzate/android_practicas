package com.example.plataformasge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plataformasge.R
import com.example.plataformasge.databinding.FragmentPersonalDataBinding

class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalDataBinding.inflate(inflater, container, false)

        //TOMAR LOS VALORES MANDADOS AL HACER LOGIN ************************+
        binding.personalDataNoControl.setText("")
        binding.personalDataStudentName.setText("")
        binding.personalDataCareer.setText("")
        binding.personalDataSemester.setText("")
        binding.personalDataPassword.setText("")

        binding.personalDataBtnUpdate.setOnClickListener {
            //llamar db para actualizar
        }


        return inflater.inflate(R.layout.fragment_personal_data, container, false)
    }

}