package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.adapters.AdapterHorario
import com.example.plataformaescolarv2.databinding.ActivityHorarioBinding
import com.example.plataformaescolarv2.getters.Horario
import com.example.plataformaescolarv2.getters.Materia
import com.example.plataformaescolarv2.viewmodels.MateriasViewModel

class HorarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorarioBinding

    private val viewModel: MateriasViewModel by viewModels()
    private lateinit var dias : List<Horario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayDias = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

        viewModel.listaMaterias.observe(this, { lista ->
            for (dia in arrayDias){
                dias = listOf(Horario(dia, lista))
            }
            binding.recyclerHorario.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.recyclerHorario.adapter = AdapterHorario(this, dias)
        })


    }

    private fun listaHorarios() : MutableList<Horario>{
        var horarios = mutableListOf<Horario>()
        var materias1 = mutableListOf<Materia>()
        materias1.add(Materia("DADM", "amaro", "09","f1", "A"))
        materias1.add(Materia("1DADM", "amaro", "09","f1", "A"))
        materias1.add(Materia("2DADM", "amaro", "09","f1", "A"))


        horarios.add(Horario("Lunes", materias1))
        horarios.add(Horario("Martes", materias1))

        return horarios

    }
}