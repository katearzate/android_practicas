package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.adapters.AdapterHorario
import com.example.plataformaescolarv2.databinding.ActivityHorarioBinding
import com.example.plataformaescolarv2.getters.Horario
import com.example.plataformaescolarv2.getters.Materia
import com.example.plataformaescolarv2.getters.MateriaHorarios

class HorarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorarioBinding

    private lateinit var dias : ArrayList<Horario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listaMaterias = intent.getSerializableExtra("materias") as ArrayList<Materia>
        val arrayDias = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

        //dias.add(Horario("Lunes", listaMaterias))

        var materias : ArrayList<Horario> = arrayListOf()
        var arrayHorario : ArrayList<MateriaHorarios> = arrayListOf()
        arrayDias.forEach { dia ->
            listaMaterias.forEach { materia ->
                arrayHorario.add(
                        MateriaHorarios(
                                materia.nombreMateria,
                                materia.profesor,
                                materia.grupo,
                                materia.dia1,
                                materia.dia2,
                                materia.dia3,
                                materia.dia4,
                                materia.dia5,))
            }
            materias.add(Horario(dia, arrayHorario))
        }

        binding.recyclerHorario.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerHorario.adapter = AdapterHorario(this, materias)




    }

    /*
    private fun listaHorarios() : MutableList<Horario>{
        var horarios = mutableListOf<Horario>()
        var materias1 = mutableListOf<Materia>()
        materias1.add(Materia("DADM", "amaro", "09","f1", "A"))
        materias1.add(Materia("1DADM", "amaro", "09","f1", "A"))
        materias1.add(Materia("2DADM", "amaro", "09","f1", "A"))


        horarios.add(Horario("Lunes", materias1))
        horarios.add(Horario("Martes", materias1))

        return horarios
    }*/

}