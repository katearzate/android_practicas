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

        val lista : MutableList<Calificacion> = mutableListOf()
        noSemestre = resources.getStringArray(R.array.numSemestres)[position]

        when(position){
            0 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre1")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            1 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre2")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            2 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre3")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            3 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre4")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            4 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre5")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            5 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre6")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
            6 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("semestre7")
                for (i in 0..(arrayMaterias.length() - 1)) {
                    val jsonMateria = arrayMaterias.getJSONObject(i)
                    lista.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
                }
            }
        }
        binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}