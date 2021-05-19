package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Calificacion

class AdapterEleccionItems(var context: Context, var materias: MutableList<Calificacion>)
    : RecyclerView.Adapter<AdapterEleccionItems.ViewHolder>(){

    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewCalificacion = itemView.findViewById<TextView>(R.id.textViewCalificacionReticula)
        val textViewNombreMateria = itemView.findViewById<TextView>(R.id.textViewNombreMateriaReticula)
        //val btnSeleccionarMateria = itemView.findViewById<Button>(R.id.btnSeleccionarMateria)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEleccionItems.ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.lista_reticula, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: AdapterEleccionItems.ViewHolder, position: Int) {
        var materia : Calificacion = materias.get(position)
        holder.textViewCalificacion.text = materia.calificacion
        holder.textViewNombreMateria.text = materia.nomMateriaCalificacion
        //holder.btnSeleccionarMateria
    }

    override fun getItemCount(): Int = materias.size



}