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

        when(position){
            0 -> {
                var lista = semestreJson("semestre1", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            1 -> {
                var lista = semestreJson("semestre2", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            2 -> {
                var lista = semestreJson("semestre3", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            3 -> {
                var lista = semestreJson("semestre4", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            4 -> {
                var lista = semestreJson("semestre5", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            5 -> {
                var lista = semestreJson("semestre6", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            6 -> {
                var lista = semestreJson("semestre7", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            7 -> {
                var lista = semestreJson("semestre8", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
            8 -> {
                var lista = semestreJson("semestre9", jsonMaterias1)
                binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
            }
        }
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