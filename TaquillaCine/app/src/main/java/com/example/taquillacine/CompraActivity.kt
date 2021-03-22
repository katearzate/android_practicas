package com.example.taquillacine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.taquillacine.databinding.ActivityCompraBinding

class CompraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val imagen = findViewById<ImageView>(R.id.imageView)

        when(bundle!!.getString("pelicula")) {
            "Gravedad","Gravity" -> imagen.setImageResource(R.drawable.gravity)
            "Interestelar","Interestellar" -> imagen.setImageResource(R.drawable.interstellar)
            "El Marciano","The Martian" -> imagen.setImageResource(R.drawable.the_martian)
           // resources.getStringArray()
        }

        var gracias = resources.getString(R.string.gracias)
        binding.txtCompra.text = """
            |¡¡$gracias!!
            |
            |${resources.getString(R.string.pelicula)}: ${bundle!!.getString("pelicula")}
            |${resources.getString(R.string.sala)}: ${bundle!!.getString("sala")}
            |${resources.getString(R.string.horario)}: ${bundle!!.getString("horario")}
            |${resources.getString(R.string.noBoletos)}: ${bundle!!.getString("noBoletos")}
            |
            |
            |${resources.getString(R.string.disfrute)}
        """.trimMargin()
    }
//|Horario: ${datos?.get(2)}
}