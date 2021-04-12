package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.AdapterCalificacion
import com.example.plataformaescolar.clases.AdapterMaterias
import com.example.plataformaescolar.clases.Calificacion
import com.example.plataformaescolar.clases.Materia
import com.example.plataformaescolar.databinding.ActivityCalificacionesBinding

class CalificacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalificacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalificacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista : MutableList<Calificacion> = mutableListOf()
        lista.add(Calificacion("85","Programacion Web"))
        lista.add(Calificacion("98","Negocios Electronicos I"))
        lista.add(Calificacion("88","Tecnologias Inalambricas"))
        lista.add(Calificacion("94","Taller de Ingenieria de Software"))
        lista.add(Calificacion("98","Bases de Datos Distribuidas"))
        lista.add(Calificacion("98","Analisis de Señales"))

        binding.listViewCalificaciones.adapter = AdapterCalificacion(this, R.layout.lista_calificaciones, lista)
    }
}