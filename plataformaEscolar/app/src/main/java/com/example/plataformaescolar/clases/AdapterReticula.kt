package com.example.plataformaescolar.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformaescolar.R

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

        if (calificacionRet.toString().trim() == "cursando"){
            //miView.setBackgroundColor(R.color.morado)
        }
        return miView
    }
}