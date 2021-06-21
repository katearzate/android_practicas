package com.example.plataformasge.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.SubjectsElectionActivity
import com.example.plataformasge.adapters.ScheduleAdapter
import com.example.plataformasge.databinding.FragmentScheduleBinding
import com.example.plataformasge.models.Schedule
import com.example.plataformasge.models.ScheduleItems
import com.example.plataformasge.models.Subject
import com.example.plataformasge.models.ViewModelSchedule
import kotlin.collections.ArrayList

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModelSchedule by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(layoutInflater)
        val arrayDays = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

        //viewModel.listSubjects.observe(viewLifecycleOwner, Observer{ subjects ->
        parentFragmentManager.setFragmentResultListener("TextoBundle", this) { key, bundle ->
            val subjects = bundle.getParcelableArrayList<Subject>("bundleKey")
            showComponents()

            var arraySchedule: ArrayList<Schedule> = arrayListOf()
            var arrayScheduleItems: ArrayList<ScheduleItems> = arrayListOf()
            arrayDays.forEach { day ->
                subjects?.forEach {
                    arrayScheduleItems.add(
                        ScheduleItems(
                            it.subjectName,
                            it.profesor,
                            it.group,
                            it.hourMonday,
                            it.hourTuesday,
                            it.hourWednesday,
                            it.hourThursday,
                            it.hourFriday
                        )
                    )
                }
                arraySchedule.add(Schedule(day, arrayScheduleItems))
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
        }

        binding.scheduleBtnCreateSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_electionFragment)
            //startActivity(Intent(requireContext(), SubjectsElectionActivity::class.java))
        }

        return binding.root
    }

    fun showComponents(){
        binding.scheduleText.visibility = View.GONE
        binding.scheduleBtnCreateSchedule.visibility = View.GONE
        binding.recyclerSchedule.visibility = View.VISIBLE
    }
}