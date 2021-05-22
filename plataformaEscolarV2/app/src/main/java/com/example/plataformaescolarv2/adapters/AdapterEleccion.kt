package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Calificacion
import com.example.plataformaescolarv2.getters.ClasesDisponibles
import com.example.plataformaescolarv2.getters.Materia
import com.example.plataformaescolarv2.getters.Semestre

abstract class AdapterEleccion(var context: Context, var semestres: MutableList<Semestre>)
    : RecyclerView.Adapter<AdapterEleccion.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewSemestre : TextView = itemView.findViewById(R.id.textViewEleccionSemestres)
        val recyclerMaterias : RecyclerView = itemView.findViewById(R.id.recyclerEleccionItems)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_eleccion, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var semestre : Semestre = semestres.get(position)
        holder.textViewSemestre.text = semestre.semestre

        llamarRecycler(holder.recyclerMaterias, semestre.materias)
    }

    override fun getItemCount(): Int = semestres.size

    private fun llamarRecycler(recyclerView: RecyclerView, materias: MutableList<ClasesDisponibles>){
        recyclerView.layoutManager = GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
        recyclerView.adapter = object : AdapterEleccionItems(context, materias){
            override fun clickClaseItem(materia: Materia) {
                clickClaseSeleccionada(materia)
            }

        }
    }

    abstract fun clickClaseSeleccionada(materia: Materia)
}