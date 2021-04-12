package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.plataformaescolar.clases.MiAdapter
import com.example.plataformaescolar.clases.materia
import com.example.plataformaescolar.databinding.ActivityHorarioBinding

class horarioActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityHorarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_horario)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val lista1 : MutableList<materia> = mutableListOf()
        lista1.add(materia("10:00","Redes de computadoras"))
        lista1.add(materia("14:00","Sistemas operativos"))

        val lista2 : MutableList<materia> = mutableListOf()
        lista2.add(materia("10:00","Redes de computadoras"))
        val m22 = materia("14:00","Sistemas operativos")
        lista2.add(m22)

        binding.listViewLunes.adapter = MiAdapter(this, R.layout.materia, lista1)
        binding.listViewMartes.adapter = MiAdapter(this, R.layout.materia, lista2)


    }
}