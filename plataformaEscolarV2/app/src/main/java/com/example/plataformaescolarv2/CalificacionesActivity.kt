package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolarv2.adapters.AdapterCalificacion
import com.example.plataformaescolarv2.databinding.ActivityCalificacionesBinding
import com.example.plataformaescolarv2.getters.Calificacion
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
    }
}