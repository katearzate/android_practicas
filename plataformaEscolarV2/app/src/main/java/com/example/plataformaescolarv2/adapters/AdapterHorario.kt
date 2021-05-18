package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Horario
import com.example.plataformaescolarv2.getters.Materia

class AdapterHorario(var context: Context, var dias: MutableList<Horario>):
RecyclerView.Adapter<AdapterHorario.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewDia : TextView = itemView.findViewById(R.id.listaHorarioDiadeSemana)
        val recyclerMaterias : RecyclerView = itemView.findViewById(R.id.recyclerHorarioItems)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_horario, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var horario : Horario = dias.get(position)
        holder.textViewDia.text = horario.dia

        llamarRecycler(holder.recyclerMaterias, horario.materias)
    }

    override fun getItemCount(): Int = dias.size

    private fun llamarRecycler(recyclerView: RecyclerView, horarios: MutableList<Materia>){
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = AdapterHorarioItems(context, horarios)
    }
}