package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.plataformaescolarv2.adapters.AdapterReticula
import com.example.plataformaescolarv2.databinding.ActivityReticulaBinding
import com.example.plataformaescolarv2.getters.Calificacion
import org.json.JSONObject

class ReticulaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityReticulaBinding
    private var noSemestre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReticulaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SpinnerNumeroSemestre.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val json1 = resources.getString(R.string.jsonMaterias)
        val jsonMaterias1 = JSONObject(json1)

        noSemestre = resources.getStringArray(R.array.numSemestres)[position]
        val arraySemestre = arrayOf("semestre1", "semestre2", "semestre3", "semestre4", "semestre5",
                "semestre6", "semestre7", "semestre8", "semestre9")

        var lista = semestreJson(arraySemestre[position], jsonMaterias1)
        binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun semestreJson(semestre: String, jsonMaterias1: JSONObject) : MutableList<Calificacion> {
        var arrayMaterias = jsonMaterias1.getJSONArray(semestre)
        val lista : MutableList<Calificacion> = mutableListOf()

        for (i in 0..(arrayMaterias.length() - 1)) {
            val jsonMateria = arrayMaterias.getJSONObject(i)
            lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
        }
        return lista
    }
}