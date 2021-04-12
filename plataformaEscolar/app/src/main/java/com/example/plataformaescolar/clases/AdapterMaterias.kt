package com.example.plataformaescolar.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolar.R

class AdapterMaterias (val context: Context, val layout: Int, val lista: List<Materia>) : BaseAdapter() {


    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(i: Int): Any {
        return lista[i]
    }

    //regresa identificador de la fila
    override fun getItemId(position: Int): Long {
        return -1
    }

    //recurso de interfaz (vista inflada)
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val horaMateria = miView.findViewById<TextView>(R.id.textViewHoraMateria)
        val nomMateria = miView.findViewById<TextView>(R.id.textViewNombreMateria)

        horaMateria.text = lista.get(position).hora
        nomMateria.text = lista.get(position).nomMateria

        return miView
    }
}