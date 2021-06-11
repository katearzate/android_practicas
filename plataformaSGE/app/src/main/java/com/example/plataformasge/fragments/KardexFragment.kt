package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.plataformasge.R
import com.example.plataformasge.databinding.FragmentKardexBinding

class KardexFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentKardexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKardexBinding.inflate(inflater, container, false)

        binding.kardexSpinnerNoSemester.onItemSelectedListener = this
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kardex, container, false)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var noSemester : String = resources.getStringArray(R.array.numSemestres)[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}