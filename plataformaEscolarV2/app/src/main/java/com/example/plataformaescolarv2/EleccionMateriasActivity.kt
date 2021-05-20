package com.example.plataformaescolarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.adapters.AdapterEleccion
import com.example.plataformaescolarv2.databinding.ActivityEleccionMateriasBinding
import com.example.plataformaescolarv2.getters.Calificacion
import com.example.plataformaescolarv2.getters.Materia
import com.example.plataformaescolarv2.getters.Semestre
import org.json.JSONObject

class EleccionMateriasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleccionMateriasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleccionMateriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMateriasEleccion.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerMateriasEleccion.adapter = AdapterEleccion(this, listaMaterias())
    }

    private fun listaMaterias(): MutableList<Semestre> {
        var semestres = mutableListOf<Semestre>()

        //Leer JSON desde recursos
        val json = resources.getString(R.string.jsonMaterias)
        val jsonMaterias = JSONObject(json)

        val arraySemestre = arrayOf("semestre1", "semestre2", "semestre3", "semestre4", "semestre5",
                "semestre6", "semestre7", "semestre8", "semestre9")


        for (i in arraySemestre.indices){
            var listaMaterias = semestreJson(arraySemestre[i], jsonMaterias)
            semestres.add(Semestre("Semestre ${i+1}", listaMaterias))
        }

        return semestres
    }

    private fun seleccionarSemestre(semestre: String, jsonMaterias: JSONObject) : MutableList<Materia>{
        val arrayMaterias = jsonMaterias.getJSONArray(semestre)
        val lista : MutableList<Materia> = mutableListOf()

        for (i in 0..(arrayMaterias.length()-1)){
            val jsonMateria = arrayMaterias.getJSONObject(i)
            lista.add(Materia(jsonMateria.getString("creditos"),
                    jsonMateria.getString("nombre"), jsonMateria.getString("clases")))  //acceder al arreglo 'clases'
        }
        return lista
    }

    private fun semestreJson(semestre: String, jsonMaterias1: JSONObject) : MutableList<Calificacion> {
        var arrayMaterias = jsonMaterias1.getJSONArray(semestre)
        val listaMaterias : MutableList<Calificacion> = mutableListOf()

        for (i in 0..(arrayMaterias.length() - 1)) {
            val jsonMateria = arrayMaterias.getJSONObject(i)
            listaMaterias.add(Calificacion(jsonMateria.getString("cali"), jsonMateria.getString("nombre")))
        }
        return listaMaterias
    }
}