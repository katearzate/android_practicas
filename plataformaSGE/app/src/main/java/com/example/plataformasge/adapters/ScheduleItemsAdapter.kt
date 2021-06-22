package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.models.ScheduleItems

class ScheduleItemsAdapter(var context: Context, var subjects: List<ScheduleItems>)
    : RecyclerView.Adapter<ScheduleItemsAdapter.ViewHolder>() {

    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val hour : TextView = itemView.findViewById(R.id.recyclerScheduleItemHour)
        val subject : TextView = itemView.findViewById(R.id.recyclerScheduleItemSubject)
        val professor : TextView = itemView.findViewById(R.id.recyclerScheduleItemProfesor)
        val group : TextView = itemView.findViewById(R.id.recyclerScheduleItemGroup)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_schedule_items,
            parent,
            false
        )

        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var subject : ScheduleItems = subjects.get(position)

        holder.subject.text = subject.subject
        holder.professor.text = "Profesor: ${subject.profesor} / "
        holder.group.text = "Grupo: ${subject.group}"
        holder.hour.text = subject.day
        /*
        when(position){
            0 -> holder.hour.text = subject.hourMonday
            1 -> holder.hour.text = subject.hourTuesday
            2 -> holder.hour.text = subject.hourWednesday
            3 -> holder.hour.text = subject.hourThursday
            4 -> holder.hour.text = subject.hourFriday
        }*/
    }

    override fun getItemCount(): Int = subjects.size
}