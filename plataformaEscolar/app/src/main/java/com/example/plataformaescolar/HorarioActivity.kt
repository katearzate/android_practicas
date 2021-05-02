package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.plataformaescolar.clases.AdapterMaterias
import com.example.plataformaescolar.clases.Calificacion
import com.example.plataformaescolar.clases.Materia
import com.example.plataformaescolar.databinding.ActivityHorarioBinding
import org.json.JSONObject

class HorarioActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var  binding: ActivityHorarioBinding
    private var diaDeLaSemana = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_horario)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerDiaDeLaSemana.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val lista : MutableList<Materia> = mutableListOf()
        diaDeLaSemana = resources.getStringArray(R.array.diasDeLaSemana)[p2]

        when(p2){
            0-> {

                lista.add(Materia("08:00", "Administracion y Seguridad de Redes"))
                lista.add(Materia("10:00","Redes de Computadoras"))
                lista.add(Materia("13:00","Telecomunicaciones"))
                lista.add(Materia("14:00","Sistemas Operativos"))
                lista.add(Materia("15:00", "Introduccion a la Seguridad Informatica"))
                lista.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))
                //binding.listViewDiaLaboral.adapter = AdapterMaterias(this, R.layout.lista_materias, lista)
            }
            1 -> {
                lista.add(Materia("08:00", "Administracion y Seguridad de Redes"))
                lista.add(Materia("09:00","Redes de Computadoras"))
                lista.add(Materia("13:00","Telecomunicaciones"))
                lista.add(Materia("14:00","Sistemas Operativos"))
                lista.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))
            }
            2 -> {
                lista.add(Materia("10:00","Redes de computadoras"))
                lista.add(Materia("13:00","Telecomunicaciones"))
                lista.add(Materia("14:00","Sistemas operativos"))
                lista.add(Materia("15:00", "Introduccion a la Seguridad Informatica"))
            }
            3 -> {
                lista.add(Materia("08:00", "Administracion y Seguridad de Redes"))
                lista.add(Materia("09:00","Redes de Computadoras"))
                lista.add(Materia("13:00","Telecomunicaciones"))
                lista.add(Materia("14:00","Sistemas Operativos"))
                lista.add(Materia("17:00", "Desarrollo de Dispositivos Moviles"))
            }
            4 -> {
                lista.add(Materia("09:00","Redes de Computadoras"))
                lista.add(Materia("13:00","Telecomunicaciones"))
                lista.add(Materia("14:00","Sistemas Operativos"))
            }
        }
        binding.listViewDiaLaboral.adapter = AdapterMaterias(this, R.layout.lista_materias, lista)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}