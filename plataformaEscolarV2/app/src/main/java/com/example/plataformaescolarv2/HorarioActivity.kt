package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.adapters.AdapterHorario
import com.example.plataformaescolarv2.databinding.ActivityHorarioBinding
import com.example.plataformaescolarv2.getters.Materia

class HorarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerHorario.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.recyclerHorario.adapter = AdapterHorario(this, listaHorarios())

    }

    private fun listaHorarios() : MutableList<String>{
        var horarios = mutableListOf<String>()
        horarios.add("Lunes")
        horarios.add("Martes")
        horarios.add("Miércoles")
        horarios.add("Jueves")
        horarios.add("Viernes")
        return horarios
    }
}