package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformasge.R
import com.example.plataformasge.models.Score

class KardexAdapter (val context: Context, val layout: Int, val list: List<Score>) : BaseAdapter(){

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflater.inflate(layout, null)

        val score = myView.findViewById<TextView>(R.id.listGradesScore)
        val credits = myView.findViewById<TextView>(R.id.listGradesCredits)
        val subject = myView.findViewById<TextView>(R.id.listGradesSubjectName)

        score.text = list.get(position).score
        credits.text = list.get(position).credits.toString()
        subject.text = list.get(position).subjectName

        if (score.text.toString() == "no cursada" || score.text.toString() == "cursando"){
            score.text = "-"
        }

        return myView
    }

}