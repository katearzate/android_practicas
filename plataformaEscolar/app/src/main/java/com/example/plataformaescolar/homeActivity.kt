package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.databinding.ActivityHomeBinding


class homeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calificaciones.setOnClickListener {
            val intent1 = Intent(this, calificacionesActivity::class.java)
            startActivity(intent1)
        }

        binding.horario.setOnClickListener {
            val intent2 = Intent(this, horarioActivity::class.java)
            startActivity(intent2)
        }

        binding.reticula.setOnClickListener {
            val intent3 = Intent(this, reticulaActivity::class.java)
            startActivity(intent3)
        }

        binding.datosPersonales.setOnClickListener {
            val intent4 = Intent(this, datosPersonalesActivity::class.java)
            startActivity(intent4)
        }

    }
}