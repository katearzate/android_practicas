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

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}