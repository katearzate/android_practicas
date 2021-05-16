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
        val arraySemestre = arrayOf("semestre1", "semestre2", "semestre3", "semestre4", "semestre5",
                "semestre6", "semestre7", "semestre8", "semestre9")

        var lista = seleccionarSemestre(arraySemestre[position], jsonMaterias)
        binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
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