package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Materia
import com.example.plataformaescolarv2.getters.MateriaHorarios

class AdapterHorarioItems(var context: Context, var materias: List<MateriaHorarios>)
    : RecyclerView.Adapter<AdapterHorarioItems.ViewHolder>(){

    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewHora : TextView = itemView.findViewById(R.id.horarioItemHora)
        val textViewMateria : TextView = itemView.findViewById(R.id.horarioItemMateria)
        val textViewProfe : TextView = itemView.findViewById(R.id.horarioItemProfesor)
        val textViewGrupo : TextView = itemView.findViewById(R.id.horarioItemGrupo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_horario_items, parent, false)


        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var materia : MateriaHorarios = materias.get(position)

        holder.textViewMateria.text = materia.nombreMateria
        holder.textViewProfe.text = "Profesor: ${materia.profesor} / "
        holder.textViewGrupo.text = "Grupo: ${materia.grupo}"
        holder.textViewHora.text = materia.dia2

        /*
        val listaHorario : Array<String?> = Array(5) {index -> ""}
        val listaAulas : Array<String?> = Array(5) {index -> ""}
        if (materia.horarios != null){
            for (i in 0..materia.horarios!!.length()-1){
                listaHorario[i] = (materia?.horarios?.optString(i, "") as String)
                listaAulas[i] = (materia?.aulas?.optString(i, "") as String)
            }
            holder.textViewHora.text = "${listaHorario[0].orEmpty()} / ${holder.textViewProfe}/ ${listaAulas[0].orEmpty()}"
        }*/


    }

    override fun getItemCount(): Int = materias.size

}