package com.example.plataformasge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.scaleMatrix
import com.example.plataformasge.R
import com.example.plataformasge.models.Score

class ReticulaAdapter (val context: Context, val layout: Int, val list: List<Score>) : BaseAdapter(){
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflater.inflate(layout, null)

        val subject = myView.findViewById<TextView>(R.id.listSubjectScore)
        val score = myView.findViewById<TextView>(R.id.listSubjectName)
        val background = myView.findViewById<LinearLayout>(R.id.listSubjectBackground)

        subject.text = list.get(position).subjectName
        score.text = list.get(position).score

        when(score.text.toString()){
            "cursando" -> {
                background.setBackgroundColor(context.resources.getColor(R.color.matCursando))
            }
            "no cursada" -> {
                background.setBackgroundColor(context.resources.getColor(R.color.matDisponible))
                score.visibility = View.GONE
            }
            "reprobada" -> {
                background.setBackgroundColor(context.resources.getColor(R.color.matReprobada))
            }
            "ACA" -> {
                background.setBackgroundColor(context.resources.getColor(R.color.extracurricular))
            }
            else -> {
                if (score.text.toString().toInt() < 70){
                    background.setBackgroundColor(context.resources.getColor(R.color.matSinAcreditar))
                }
            }
        }

        return myView
    }

}