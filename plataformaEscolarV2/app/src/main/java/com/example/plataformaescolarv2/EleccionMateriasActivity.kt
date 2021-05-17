package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolarv2.databinding.ActivityEleccionMateriasBinding

class EleccionMateriasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleccionMateriasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleccionMateriasBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}