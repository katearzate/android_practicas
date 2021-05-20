package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Materia

class AdapterMateriasElegidas(val context: Context, val layout: Int, val listaMaterias: List<Materia>) : BaseAdapter() {
    override fun getCount(): Int = listaMaterias.size

    override fun getItem(position: Int): Any {
        return listaMaterias[position]
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)
/*
        val tvCreditos : TextView = miView.findViewById(R.id.elegidaNumeroCreditos)
        val tvMateria : TextView = miView.findViewById(R.id.elegidaNombreMateria)
        val tvHora : TextView = miView.findViewById(R.id.elegidaHoraMateria)
        val tvProfesor : TextView = miView.findViewById(R.id.elegidaNombreProfe)

        tvCreditos.text = listaMaterias.get(position).creditos.toString()
        tvMateria.text = listaMaterias.get(position).materia
        tvHora.text = listaMaterias.get(position).hora
        tvProfesor.text = listaMaterias.get(position).profesor
*/
        return miView
    }


}