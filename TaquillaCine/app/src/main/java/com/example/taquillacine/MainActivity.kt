package com.example.taquillacine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.taquillacine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private var pelicula = ""
    private var horario = ""
    private var sala = ""
    private var nBoletos = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listViewPeliculas.onItemClickListener = this

        val salas = arrayOf("Normal","4DX")
        binding.listViewSala.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, salas)
        binding.listViewSala.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long) {
                view?.let {
                    sala = salas[index]
                    actualizaSeleccion()
                }
            }
        }

        val boletos = ArrayList<String>()
        for(i in 1..10) {
            boletos.add("$i")
        }
        binding.spinnerNumBoletos.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, boletos)

        binding.spinnerNumBoletos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p1?.let {
                    val textView = it.findViewById<TextView>(android.R.id.text1)
                    nBoletos = textView.text.toString()
                    actualizaSeleccion()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.spinnerHorarios.onItemSelectedListener = this

        binding.btnComprar.setOnClickListener {
            val intent = Intent(this, CompraActivity::class.java)
            val datos = arrayOf(pelicula,sala,horario,nBoletos)
            intent.putExtra("datos",datos)
            startActivity(intent)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p1?.let {
            // Es un casteo que en Java seria (TextView)it
            val textView = it as TextView
            pelicula = "" + textView.text
            actualizaSeleccion()
        }
    }

    private fun actualizaSeleccion() {
        binding.textSeleccion.text = """
           arregla esto ahora : $pelicula
           Sala: $sala
           Horario: $horario
           Boletos: $nBoletos
        """.trimIndent()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        horario = resources.getStringArray(R.array.horarios)[p2]
        actualizaSeleccion()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}