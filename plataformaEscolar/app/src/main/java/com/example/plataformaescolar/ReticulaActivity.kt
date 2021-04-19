package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.plataformaescolar.clases.AdapterCalificacion
import com.example.plataformaescolar.clases.AdapterReticula
import com.example.plataformaescolar.clases.Calificacion
import com.example.plataformaescolar.databinding.ActivityReticulaBinding
import org.json.JSONObject

class ReticulaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding : ActivityReticulaBinding

    private var numSemestre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReticulaBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        val json = resources.getString(R.string.jsonMaterias)
        val jsonMaterias = JSONObject(json)
        val arrayMaterias1 = jsonMaterias.getJSONArray("semestre1")

        val lista : MutableList<Calificacion> = mutableListOf()

        for (i in 0..(arrayMaterias1.length()-1)){
            val jsonMateria = arrayMaterias1.getJSONObject(i)
            lista.add(Calificacion(jsonMateria.getString("cali"),jsonMateria.getString("nombre")))
        }
        */
  //      binding.listViewReticula.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)

        //***************** numero de semestres ********************
        binding.SpinnerNumeroSemestre.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val json1 = resources.getString(R.string.jsonMaterias)
        val jsonMaterias1 = JSONObject(json1)
        //var arrayMaterias : JSONObject

        val lista : MutableList<Calificacion> = mutableListOf()
        numSemestre = resources.getStringArray(R.array.numSemestres)[p2]

        when(p2){
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

    }//fin del spinner de numero de semestre

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}