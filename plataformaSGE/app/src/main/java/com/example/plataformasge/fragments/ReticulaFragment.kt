package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.plataformasge.R
import com.example.plataformasge.adapters.ReticulaAdapter
import com.example.plataformasge.databinding.FragmentKardexBinding
import com.example.plataformasge.databinding.FragmentReticulaBinding
import com.example.plataformasge.models.DBManager
import java.lang.Exception

class ReticulaFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentReticulaBinding? = null
    private val binding get() = _binding!!

    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReticulaBinding.inflate(layoutInflater)
        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        binding.reticulaSpinnerNoSemester.onItemSelectedListener = this
        //binding.listReticulaSubject.adapter = ReticulaAdapter(requireContext(), R.layout.list_reticula_subject, lista)

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var noSemester: String = (position+1).toString()

        try {
            binding.listReticulaSubject.adapter = ReticulaAdapter(
                requireContext(),
                R.layout.list_reticula_subject,
                dbManager.showScores(noSemester)
            )
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


}