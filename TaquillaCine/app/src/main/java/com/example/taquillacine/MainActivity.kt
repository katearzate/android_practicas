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

        //******************** SALAS **************************
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

        //******************* BOLETOS *************************
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

        //***************** BOTON COMPRA ************************
        binding.btnComprar.setOnClickListener {
            val intent = Intent(this, CompraActivity::class.java)
            var bundle = Bundle()
            bundle.putString("pelicula",pelicula)
            bundle.putString("sala",sala)
            bundle.putString("horario",horario)
            bundle.putString("noBoletos",nBoletos)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p1?.let {
            val textView = it as TextView
            pelicula = "" + textView.text
            actualizaSeleccion()
        }
    }

    private fun actualizaSeleccion() {
        binding.textSeleccion.text = """${resources.getString(R.string.eleccion)}:
            
           ${resources.getString(R.string.pelicula)}: $pelicula
           ${resources.getString(R.string.sala)}: $sala
           ${resources.getString(R.string.horario)}: $horario
           ${resources.getString(R.string.noBoletos)}: $nBoletos
        """.trimIndent()
    }

    //************************* HORARIO ***********************
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        horario = resources.getStringArray(R.array.horarios)[p2]
        actualizaSeleccion()
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {}
}