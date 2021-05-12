package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.databinding.ActivityEleccionBinding

class EleccionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleccionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleccionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}