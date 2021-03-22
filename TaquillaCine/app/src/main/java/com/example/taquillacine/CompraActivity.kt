package com.example.taquillacine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taquillacine.databinding.ActivityCompraBinding

class CompraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datos = intent.getStringArrayExtra("datos")

        binding.txtCompra.text = """¡¡Gracias por tu compra!!!
            |
            |Película: ${datos?.get(0)}
            |Sala: ${datos?.get(1)}
            |Horario: ${datos?.get(2)}
            |Boletos: 
        """.trimMargin()
    }

}