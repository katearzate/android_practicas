package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Calificacion

class AdapterReticula (val context: Context, val layout: Int, val lista: List<Calificacion>) : BaseAdapter(){
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(i: Int): Any {
        return lista[i]
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val calificacionRet = miView.findViewById<TextView>(R.id.textViewCalificacionReticula)
        val nomMateriaRet = miView.findViewById<TextView>(R.id.textViewNombreMateriaReticula)

        calificacionRet.text = lista.get(position).calificacion
        nomMateriaRet.text = lista.get(position).nomMateriaCalificacion

        val conjuntoRet = miView.findViewById<LinearLayout>(R.id.Reticulabackground)

        if (calificacionRet.text.toString() == "cursando"){
            conjuntoRet.setBackgroundColor(context.resources.getColor(R.color.matCursando))
        }else if(calificacionRet.text.toString() == "no cursada"){
            conjuntoRet.setBackgroundColor(context.resources.getColor(R.color.matDisponible))
        }else if (calificacionRet.text.toString().toInt() < 70){
            conjuntoRet.setBackgroundColor(context.resources.getColor(R.color.matSinAcreditar))
        }else if (calificacionRet.text.toString() == "reprobada"){
            conjuntoRet.setBackgroundColor(context.resources.getColor(R.color.matReprobada))
        }else if (calificacionRet.text.toString() == "ACA"){
            conjuntoRet.setBackgroundColor(context.resources.getColor(R.color.extracurricular))
        }

        return miView
    }
}