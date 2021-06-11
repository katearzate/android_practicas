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

class ReticulaFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentReticulaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReticulaBinding.inflate(inflater, container, false)

        binding.reticulaSpinnerNoSemester.onItemSelectedListener = this
        //binding.listReticulaSubject.adapter = ReticulaAdapter(requireContext(), R.layout.list_reticula_subject, lista)

        return inflater.inflate(R.layout.fragment_reticula, container, false)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var noSemester : String = resources.getStringArray(R.array.numSemestres)[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


}