package com.example.plataformasge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.adapters.ElectionAdapter
import com.example.plataformasge.adapters.SubjectedSelectedAdapter
import com.example.plataformasge.databinding.ActivitySubjectsElectionBinding
import com.example.plataformasge.fragments.ScheduleFragment
import com.example.plataformasge.models.*
import java.util.ArrayList

class SubjectsElectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubjectsElectionBinding
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!
    private val viewModel: ViewModelSchedule by viewModels()

    private var listSubjectSelected : ArrayList<Subject> = arrayListOf()
    private var totalCredits : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsElectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _dbManager = DBManager(this, "escolar", null, 1)

        var user = intent.getParcelableExtra<User>("user")

        binding.recyclerElection.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        var semesters = arrayListOf<Semester>()
        for (s in 1..8){
            semesters.add(Semester(s.toString(), dbManager.showScores(s.toString())))
        }
        binding.recyclerElection.adapter = object : ElectionAdapter(this, semesters){
            override fun groupSelectedElection(group: Subject) {
                totalCredits += group.credits!!
                binding.electionTotalCredits.visibility = View.VISIBLE

                if (totalCredits <= 36){
                    listSubjectSelected.add(group)
                    binding.electionListofSubjectsSelected.adapter = SubjectedSelectedAdapter(
                        this@SubjectsElectionActivity,
                        R.layout.list_subjects_selected,
                        listSubjectSelected
                    )
                }else{
                    showAlert("L??mite de cr??ditos", "No puedes agregar m??s materias, se ha excedido el l??mite")
                    totalCredits -= group.credits!!
                }
                binding.electionTotalCredits.setText("Total de cr??ditos: ${totalCredits}")
            }
        }

        binding.electionBtnRegisterSchedule.setOnClickListener {
            if (totalCredits < 15){
                showAlert("Error", "Debes seleccionar m??s materias para poder registrar el horario")
            }else{
                listSubjectSelected.forEach { subject ->
                    dbManager.insertSubjects(subject)
                }
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("user", user)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showAlert(error: String, mensaje: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(error)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}