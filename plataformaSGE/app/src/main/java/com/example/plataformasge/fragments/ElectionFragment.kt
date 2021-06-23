package com.example.plataformasge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.adapters.ElectionAdapter
import com.example.plataformasge.adapters.SubjectedSelectedAdapter
import com.example.plataformasge.databinding.FragmentElectionBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.Semester
import com.example.plataformasge.models.Subject
import com.example.plataformasge.models.ViewModelSchedule
import java.util.ArrayList

class ElectionFragment: Fragment() {
    private var _binding: FragmentElectionBinding? = null
    private val binding get() = _binding!!
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    private val viewModel: ViewModelSchedule by activityViewModels()

    private var listSubjectSelected : ArrayList<Subject> = arrayListOf()
    private var totalCredits : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentElectionBinding.inflate(layoutInflater)

        _dbManager = DBManager(requireContext(), "escolar", null, 1)

        binding.recyclerElection.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        var semesters = arrayListOf<Semester>()
        for (s in 5..8){
            semesters.add(Semester(s.toString(), dbManager.showScores(s.toString())))
        }
        binding.recyclerElection.adapter = object : ElectionAdapter(requireContext(), semesters){
            override fun groupSelectedElection(group: Subject) {
                totalCredits += group.credits!!
                binding.electionTotalCredits.visibility = View.VISIBLE

                if (totalCredits <= 36){
                    listSubjectSelected.add(group)
                    binding.electionListofSubjectsSelected.adapter = SubjectedSelectedAdapter(
                        requireContext(),
                        R.layout.list_subjects_selected,
                        listSubjectSelected
                    )
                }else{
                    showAlert("Límite de créditos", "No puedes agregar más materias, se ha excedido el límite")
                    totalCredits -= group.credits!!
                }
                binding.electionTotalCredits.setText("Total de créditos: ${totalCredits}")
            }
        }

        binding.electionBtnRegisterSchedule.setOnClickListener {
            var subjectList: ArrayList<Subject> = arrayListOf()

            if (totalCredits< 3){
                showAlert("Error", "Debes seleccionar más materias para poder registrar el horario")
            }else{
                listSubjectSelected.forEach { subject ->
                    //subjectList.add(subject)
                    dbManager.insertSubjects(subject)
                    println("Insercion exitosa!")
                }

                /*childFragmentManager.setFragmentResult("subjects", bundleOf("bundleKey" to subjectList))

                viewModel.setList(subjectList)
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("subjects", subjectList)
                startActivity(intent)
                finish()*/
            }
        }

        return binding.root
    }

    private fun showAlert(error: String, mensaje: String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(error)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}