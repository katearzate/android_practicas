package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.models.Schedule
import com.example.plataformasge.models.ScheduleItems

class ScheduleAdapter (var context: Context, var days: List<Schedule>):
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val day : TextView = itemView.findViewById(R.id.recyclerScheduleDay)
        val recyclerSubjects : RecyclerView = itemView.findViewById(R.id.recyclerScheduleListItems)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_schedule,
            parent,
            false
        )

        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule : Schedule = days.get(position)

        holder.day.text = schedule.day

        callRecycler(holder.recyclerSubjects, schedule.subjects)
    }

    override fun getItemCount(): Int = days.size

    private fun callRecycler(recyclerView: RecyclerView, materias: List<ScheduleItems>){
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = ScheduleItemsAdapter(context, materias)
    }
}