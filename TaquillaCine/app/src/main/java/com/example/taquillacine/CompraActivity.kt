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

        val bundle = intent.extras

        var gracias = resources.getString(R.string.gracias)
        binding.txtCompra.text = """¡¡$gracias!!
            |
            |${resources.getString(R.string.pelicula)}: ${bundle!!.getString("pelicula")}
            |${resources.getString(R.string.sala)}: ${bundle!!.getString("sala")}
            |${resources.getString(R.string.horario)}: ${bundle!!.getString("horario")}
            |${resources.getString(R.string.noBoletos)}: ${bundle!!.getString("noBoletos")}
        """.trimMargin()
    }
//|Horario: ${datos?.get(2)}
}