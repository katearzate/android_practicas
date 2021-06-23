package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformasge.R
import com.example.plataformasge.models.Subject

class SubjectedSelectedAdapter(val context: Context, val layout: Int, val list: List<Subject>) : BaseAdapter()  {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflater.inflate(layout, null)

        val subjectName: TextView = myView.findViewById(R.id.subjectSelectedName)
        val profesor: TextView = myView.findViewById(R.id.subjectSelectedProfesor)
        val credits: TextView = myView.findViewById(R.id.subjectSelectedCredits)
        val group: TextView = myView.findViewById(R.id.subjectSelectedGroup)
        val hMonday: TextView = myView.findViewById(R.id.subjectSelectedMonday)
        val hTuesday: TextView = myView.findViewById(R.id.subjectSelectedTuesday)
        val hWednesday: TextView = myView.findViewById(R.id.subjectSelectedWednesday)
        val hThursday: TextView = myView.findViewById(R.id.subjectSelectedThursday)
        val hFriday: TextView = myView.findViewById(R.id.subjectSelectedFriday)
        val deleteSubject: TextView = myView.findViewById(R.id.subjectSelectedDelete)

        val subject: Subject = list.get(position)

        subjectName.setText(subject.subjectName)
        profesor.setText(subject.profesor)
        credits.setText(subject.credits.toString())
        group.setText(subject.group)
        hMonday.setText(subject.hourMonday)
        hTuesday.setText(subject.hourTuesday)
        hWednesday.setText(subject.hourWednesday)
        hThursday.setText(subject.hourThursday)
        hFriday.setText(subject.hourFriday)


        return myView
    }
}