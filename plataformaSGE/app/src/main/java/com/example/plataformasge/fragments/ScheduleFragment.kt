package com.example.plataformasge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.adapters.ScheduleAdapter
import com.example.plataformasge.databinding.FragmentScheduleBinding
import com.example.plataformasge.models.*

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!
    private val viewModel: ViewModelSchedule by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(layoutInflater)
        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        val arrayDays = arrayOf("lunes", "martes", "miercoles", "jueves", "viernes")
        var arraySchedule: MutableList<Schedule> = mutableListOf()

        arrayDays.forEach { day ->
            arraySchedule.add(Schedule(day, dbManager.showSchedule(day)))
        }

        binding.recyclerSchedule.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerSchedule.adapter = ScheduleAdapter(
            requireContext(),
            arraySchedule
        )

        return binding.root
    }

}