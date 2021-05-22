package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Materia

class AdapterClases(val context: Context, val layout: Int, val listaClases: List<Materia>) : BaseAdapter() {
    override fun getCount(): Int {
        return listaClases.size
    }

    override fun getItem(i: Int): Any {
        return listaClases[i]
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val profesor = miView.findViewById<TextView>(R.id.materiaSeleccionableProfesor)
        val grupo = miView.findViewById<TextView>(R.id.materiaSeleccionableGrupo)
        val creditos = miView.findViewById<TextView>(R.id.materiaSeleccionableCreditos)
        val horaLunes = miView.findViewById<TextView>(R.id.materiaSeleccionableHorarioLunes)
        val horaMartes = miView.findViewById<TextView>(R.id.materiaSeleccionableHorarioMartes)
        val horaMiercoles = miView.findViewById<TextView>(R.id.materiaSeleccionableHorarioMiercoles)
        val horaJueves = miView.findViewById<TextView>(R.id.materiaSeleccionableHorarioJueves)
        val horaViernes = miView.findViewById<TextView>(R.id.materiaSeleccionableHorarioViernes)

        profesor.text = listaClases.get(position).profesor
        grupo.text = listaClases.get(position).grupo
        creditos.text = listaClases.get(position).creditos.toString()
        //horaLunes.text = listaClases.get(position).horarios[0]
        return miView
    }
}