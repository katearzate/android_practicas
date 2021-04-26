package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ActivityFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviarActivity.setOnClickListener {
            binding.editMensajeActivity.text.let {
                viewModel.setMensaje("Activity dijo: ${it}")
            }
            binding.editMensajeActivity.setText("")
        }

    }
}