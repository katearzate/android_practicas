package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.plataformaescolarv2.adapters.AdapterCalificacion
import com.example.plataformaescolarv2.databinding.ActivityCalificacionesBinding
import com.example.plataformaescolarv2.getters.Calificacion
import org.json.JSONObject

class CalificacionesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityCalificacionesBinding
    private var noSemestre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalificacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SpinnerNoSemestreCalificaciones.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val json = resources.getString(R.string.jsonMaterias)
        val jsonMaterias = JSONObject(json)

        noSemestre = resources.getStringArray(R.array.numSemestres)[position]

        when(position){
            0 -> {
                var lista = seleccionarSemestre("semestre1", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            1 -> {
                var lista = seleccionarSemestre("semestre2", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            2 -> {
                var lista = seleccionarSemestre("semestre3", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            3 -> {
                var lista = seleccionarSemestre("semestre4", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            4 -> {
                var lista = seleccionarSemestre("semestre5", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            5 -> {
                var lista = seleccionarSemestre("semestre6", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            6 -> {
                var lista = seleccionarSemestre("semestre7", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            7 -> {
                var lista = seleccionarSemestre("semestre8", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
            8 -> {
                var lista = seleccionarSemestre("semestre9", jsonMaterias)
                binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun seleccionarSemestre(semestre: String, jsonMaterias: JSONObject) : MutableList<Calificacion>{
        val arrayMaterias = jsonMaterias.getJSONArray(semestre)
        val lista : MutableList<Calificacion> = mutableListOf()

        for (i in 0..(arrayMaterias.length()-1)){
            val jsonMateria = arrayMaterias.getJSONObject(i)
            lista.add(Calificacion(jsonMateria.getString("cali"),
                    jsonMateria.getString("nombre"), jsonMateria.getString("creditos").toInt()))
        }
        return lista
    }
}