package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.plataformasge.R
import com.example.plataformasge.models.Subject
import com.google.android.material.button.MaterialButton

class GroupsAdapter (val context: Context, val layout: Int, val list: List<Subject>):
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflater.inflate(layout, null)

        val profesor: TextView = myView.findViewById(R.id.availableSubjectProfesor)
        val group: TextView = myView.findViewById(R.id.availableSubjectGroup)
        val credits: TextView = myView.findViewById(R.id.availableSubjectCredits)
        val hMonday: TextView = myView.findViewById(R.id.availableSubjectHourMonday)
        val hTuesday: TextView = myView.findViewById(R.id.availableSubjectHourTuesday)
        val hWednesday: TextView = myView.findViewById(R.id.availableSubjectHourWednesday)
        val hThursday: TextView = myView.findViewById(R.id.availableSubjectHourThursday)
        val hFriday: TextView = myView.findViewById(R.id.availableSubjectHourFriday)
        val btnSelect : MaterialButton = myView.findViewById(R.id.availableSubjectBtnSelect)

        val subject: Subject = list.get(position)

        profesor.setText(subject.profesor)
        group.setText(subject.group)
        credits.setText(subject.credits.toString())
        hMonday.setText(subject.hourMonday)
        hTuesday.setText(subject.hourTuesday)
        hWednesday.setText(subject.hourWednesday)
        hThursday.setText(subject.hourThursday)
        hFriday.setText(subject.hourFriday)

        btnSelect.setOnClickListener{
            val group : Subject = list.get(position)
            Toast.makeText(context, "MATERIA SELECCIONADA", Toast.LENGTH_LONG).show()
            //groupSelected(group)
        }

        return myView
    }

    //abstract fun groupSelected(subject: Subject)
}