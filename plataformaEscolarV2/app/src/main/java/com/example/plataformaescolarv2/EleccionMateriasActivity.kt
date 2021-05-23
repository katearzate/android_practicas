package com.example.plataformaescolarv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.adapters.AdapterEleccion
import com.example.plataformaescolarv2.adapters.AdapterMateriasElegidas
import com.example.plataformaescolarv2.databinding.ActivityEleccionMateriasBinding
import com.example.plataformaescolarv2.getters.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class EleccionMateriasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleccionMateriasBinding
    private var listaMateriasSeleccionadas : MutableList<Materia> = mutableListOf()
    private var creditosTotales : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleccionMateriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMateriasEleccion.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerMateriasEleccion.adapter = object : AdapterEleccion(this, listaMaterias()){
            override fun clickClaseSeleccionada(materia: Materia) {

                creditosTotales += materia.creditos!!
                binding.eleccionNumeroCreditosTotales.visibility = View.VISIBLE
                if (creditosTotales <= 36){
                    listaMateriasSeleccionadas.add(materia)
                    binding.listViewMateriasSeleccionadas.adapter = AdapterMateriasElegidas(this@EleccionMateriasActivity,
                            R.layout.lista_eleccion_materias,
                            listaMateriasSeleccionadas)
                } else if(creditosTotales < 20){
                    println("SE DEBE TOMAR MÁS MATERIAS")
                } else{
                    creditosTotales -= materia.creditos!!
                    println("LIMITE DE MATERIAS!!")
                    Toast.makeText(this@EleccionMateriasActivity, "Límite de creditos excedido", Toast.LENGTH_LONG).show()
                    //Toast no funciona, revisar el por qué
                }
                binding.eleccionNumeroCreditosTotales.setText("Total de créditos: ${creditosTotales}")
            }
        }

        binding.btnSubirMaterias.setOnClickListener {
            val intent = Intent(this, HorarioActivity::class.java)
            intent.putStringArrayListExtra("listaMaterias", listaMateriasSeleccionadas as ArrayList<String>)
            startActivity(intent)
            finish()
        }
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

    private fun semestreJson(semestre: String, jsonMaterias1: JSONObject) : MutableList<ClasesDisponibles> {
        var arrayMaterias = jsonMaterias1.getJSONArray(semestre)
        val listaMaterias : MutableList<ClasesDisponibles> = mutableListOf()


        for (i in 0..(arrayMaterias.length() - 1)) {
            val jsonMateria = arrayMaterias.getJSONObject(i)
            val listaClases : MutableList<Materia> = mutableListOf()

            if (jsonMateria.has("clases") == true){
                var arrayClases = jsonMateria.getJSONArray("clases")
                for (i in 0..(arrayClases.length() - 1)){
                    val jsonClases = arrayClases.getJSONObject(i)
                    listaClases.add(
                            Materia(profesor = jsonClases.getString("profe"),
                                    grupo = jsonClases.getString("grupo"),
                                    horarios = jsonClases.getJSONArray("horarios"),
                                    aulas = jsonClases.getJSONArray("aulas")))
                }
            }

            listaMaterias.add(ClasesDisponibles(
                Materia(calificacion = jsonMateria.getString("cali"),
                    nombreMateria = jsonMateria.getString("nombre"),
                    creditos = jsonMateria.getInt("creditos")),

                listaClases))
        }
        return listaMaterias
    }
}