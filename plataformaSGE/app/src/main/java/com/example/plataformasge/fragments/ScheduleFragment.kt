package com.example.plataformasge.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.plataformasge.SubjectsElectionActivity
import com.example.plataformasge.databinding.FragmentScheduleBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.ViewModelSchedule
import java.util.*

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!
    private val viewModel: ViewModelSchedule by activityViewModels()

    private val created: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(layoutInflater)
        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        if (created == true){
            binding.scheduleBtnCreateSchedule.visibility = View.GONE
            binding.scheduleText.visibility = View.GONE

            binding.recyclerSchedule.visibility = View.VISIBLE
            //TODO: SHOW SCHEDULE CREATED!
            viewModel.listSubjects.observe(viewLifecycleOwner, androidx.lifecycle.Observer { subjects ->

            })
        }

        binding.scheduleBtnCreateSchedule.setOnClickListener {
            startActivity(Intent(requireContext(), SubjectsElectionActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val spinnerCantidad = view.findViewById<Spinner>(R.id.spinnerCantidad)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalCompra)
        val btnConfirmar = view.findViewById<Button>(R.id.btnConfirmar)

        spinnerCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                tvTotal.setText("TOTAL: ${(index+1)*50.0}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
         */
    }
}