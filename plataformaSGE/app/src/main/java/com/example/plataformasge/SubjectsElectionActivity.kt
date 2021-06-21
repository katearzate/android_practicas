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
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.Semester
import com.example.plataformasge.models.Subject
import com.example.plataformasge.models.ViewModelSchedule
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

        binding.recyclerElection.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        var semesters = arrayListOf<Semester>()
        for (s in 5..8){
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
                    showAlert("Límite de créditos", "No puedes agregar más materias, se ha excedido el límite")
                    totalCredits -= group.credits!!
                }
                binding.electionTotalCredits.setText("Total de créditos: ${totalCredits}")
            }
        }

        binding.electionBtnRegisterSchedule.setOnClickListener {
            var subjectList: ArrayList<Subject> = arrayListOf()

            if (totalCredits< 10){
                showAlert("Error", "Debes seleccionar más materias para poder registrar el horario")
            }else{
                listSubjectSelected.forEach { subject ->
                    subjectList.add(subject)
                }
                val intent = Intent(this, HomeActivity::class.java)
                //intent.putExtra("subjects", subjectList)
                viewModel.setList(subjectList)
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