package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.AdapterCalificacion
import com.example.plataformaescolar.clases.AdapterMaterias
import com.example.plataformaescolar.clases.Calificacion
import com.example.plataformaescolar.clases.Materia
import com.example.plataformaescolar.databinding.ActivityCalificacionesBinding
import org.json.JSONObject

class CalificacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalificacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalificacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = resources.getString(R.string.jsonMaterias)
        val jsonMaterias = JSONObject(json)
        val arrayMaterias = jsonMaterias.getJSONArray("semestre5")

        val lista : MutableList<Calificacion> = mutableListOf()

        for (i in 0..(arrayMaterias.length()-1)){
            val jsonMateria = arrayMaterias.getJSONObject(i)
            lista.add(Calificacion(jsonMateria.getString("cali"),jsonMateria.getString("nombre")))
        }
        binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)

        /*
        lista.add(Calificacion("85","Programacion Web"))
        lista.add(Calificacion("98","Negocios Electronicos I"))
        lista.add(Calificacion("88","Tecnologias Inalambricas"))
        lista.add(Calificacion("94","Taller de Ingenieria de Software"))
        lista.add(Calificacion("98","Bases de Datos Distribuidas"))
        lista.add(Calificacion("98","Analisis de Señales"))*/


    }
}