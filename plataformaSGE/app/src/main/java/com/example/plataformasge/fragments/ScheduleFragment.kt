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
import com.example.plataformasge.models.*
import kotlin.collections.ArrayList

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


        binding.scheduleBtnCreateSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_electionFragment)
            showComponents()
            //startActivity(Intent(requireContext(), SubjectsElectionActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayDays = arrayOf("lunes", "martes", "miercoles", "jueves", "viernes")
        var arraySchedule: ArrayList<Schedule> = arrayListOf()
        var arrayScheduleItems: ArrayList<ScheduleItems> = arrayListOf()


        /*
        parentFragmentManager.setFragmentResultListener("subjects", this) { key, bundle ->
            val subjects = bundle.getParcelableArrayList<Subject>("bundleKey")
            showComponents()

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
            }*/

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

    fun showComponents(){
        binding.scheduleText.visibility = View.GONE
        binding.scheduleBtnCreateSchedule.visibility = View.GONE
        binding.recyclerSchedule.visibility = View.VISIBLE
    }
}