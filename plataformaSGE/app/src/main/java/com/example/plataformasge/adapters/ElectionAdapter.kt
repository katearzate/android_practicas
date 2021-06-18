package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformasge.R
import com.example.plataformasge.models.Semester
import com.example.plataformasge.models.Subject

class ElectionAdapter (var context: Context, var semesters: MutableList<Semester>)
    : RecyclerView.Adapter<ElectionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noSemester: TextView = itemView.findViewById(R.id.recyclerElectionNoSemester)
        val recyclerSubjects: RecyclerView = itemView.findViewById(R.id.recyclerElectionItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_election,
            parent,
            false
        )

        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var semester: Semester = semesters.get(position)
        holder.noSemester.text = semester.semester

        callRecycler(holder.recyclerSubjects, semester.subjects)
    }

    override fun getItemCount(): Int = semesters.size

    private fun callRecycler(recyclerView: RecyclerView, subjects: MutableList<Subject>) {
        recyclerView.layoutManager = GridLayoutManager(
            context,
            2,
            RecyclerView.VERTICAL,
            false
        )

        recyclerView.adapter = ElectionItemsAdapter(context, subjects)
    }

}