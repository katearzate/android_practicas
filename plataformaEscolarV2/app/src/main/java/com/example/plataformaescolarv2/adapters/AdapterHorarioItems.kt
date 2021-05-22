package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Materia

class AdapterHorarioItems(var context: Context, var materias: MutableList<Materia>)
    : RecyclerView.Adapter<AdapterHorarioItems.ViewHolder>(){

    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewHora : TextView = itemView.findViewById(R.id.horarioItemHora)
        val textViewMateria : TextView = itemView.findViewById(R.id.horarioItemMateria)
        val textViewProfe : TextView = itemView.findViewById(R.id.horarioItemProfesor)
        val textViewAula : TextView = itemView.findViewById(R.id.horarioItemAula)
        val textViewGrupo : TextView = itemView.findViewById(R.id.horarioItemGrupo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_horario_items, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var materia : Materia = materias.get(position)
        holder.textViewHora.text = materia.hora
        holder.textViewMateria.text = materia.nombreMateria
        holder.textViewProfe.text = materia.profesor
        holder.textViewAula.text = materia.aula
        holder.textViewGrupo.text = materia.grupo
    }

    override fun getItemCount(): Int = materias.size

}