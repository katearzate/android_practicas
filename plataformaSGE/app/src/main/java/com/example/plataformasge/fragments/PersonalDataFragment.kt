package com.example.plataformasge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.plataformasge.databinding.FragmentPersonalDataBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.User
import com.example.plataformasge.models.ViewModelPersonalData
import java.lang.Exception

class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelPersonalData by activityViewModels()

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
            showPersonalData(user)
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

    fun showPersonalData(user: User){
        val showUser = dbManager.findUser(user.noControl, user.password)
        binding.personalDataNoControl.setText(showUser!!.noControl)
        binding.personalDataStudentName.setText(showUser.name)
        binding.personalDataStudentLastnames.setText(showUser.lastNames)
        binding.personalDataCareer.setText(showUser.career)
        binding.personalDataSemester.setText(showUser.semester)
        binding.personalDataPassword.setText(showUser.password)
    }
}