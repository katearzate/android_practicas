package com.example.plataformasge.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.plataformasge.databinding.FragmentPersonalDataBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.User
import com.example.plataformasge.models.ViewModelHomeFragments
import java.lang.Exception

class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelHomeFragments by activityViewModels()

    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalDataBinding.inflate(layoutInflater)

        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        viewModel.userEntered.observe(viewLifecycleOwner, Observer { user ->
            binding.personalDataNoControl.setText(user.noControl)
            binding.personalDataStudentName.setText(user.name)
            binding.personalDataStudentLastnames.setText(user.lastNames)
            binding.personalDataCareer.setText(user.career)
            binding.personalDataSemester.setText(user.semester)
            binding.personalDataPassword.setText(user.password)
            println("USUARIO DESDE FRAGMENTO: "+user)


            binding.personalDataBtnUpdate.setOnClickListener {
                try {
                    dbManager.updateUser(
                        User(
                            user!!.id,
                            binding.personalDataStudentName.text.toString(),
                            binding.personalDataStudentLastnames.text.toString(),
                            binding.personalDataNoControl.text.toString(),
                            binding.personalDataPassword.text.toString(),
                            binding.personalDataCareer.text.toString(),
                            binding.personalDataSemester.text.toString()
                        )
                    )
                    println("Usuario actualizado!!!")
                    Toast.makeText(requireContext(), "Usuario actualizado", Toast.LENGTH_LONG)

                } catch (e: Exception){
                    println("Error al actualizar!")
                    e.printStackTrace()
                }
            }
        })



        return binding.root
    }

}