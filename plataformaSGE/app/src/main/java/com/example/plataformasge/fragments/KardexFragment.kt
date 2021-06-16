package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.plataformasge.R
import com.example.plataformasge.adapters.KardexAdapter
import com.example.plataformasge.databinding.FragmentKardexBinding
import com.example.plataformasge.models.DBManager

class KardexFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentKardexBinding? = null
    private val binding get() = _binding!!

    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKardexBinding.inflate(layoutInflater)
        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        binding.kardexSpinnerNoSemester.onItemSelectedListener = this

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //var noSemester : String = resources.getStringArray(R.array.numSemestres)[position]
        var noSemester: String = (position+1).toString()

        binding.listKardexGrades.adapter = KardexAdapter(
            requireContext(),
            R.layout.list_kardex_grades,
            dbManager.showScores(noSemester)
        )

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}