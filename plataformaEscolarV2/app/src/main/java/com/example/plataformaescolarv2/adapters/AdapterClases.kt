package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Materia

class AdapterClases(val context: Context, val layout: Int, val listaClases: List<Materia>, val creditos: Int) : BaseAdapter() {
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

        val materia : Materia = listaClases.get(position)

        profesor.text = materia.profesor
        grupo.text = materia.grupo
        creditos.text = this.creditos.toString()

        val listaHorario : Array<String?> = Array(5) {index -> ""}
        val listaAulas : Array<String?> = Array(5) {index -> ""}
        if (materia.horarios != null){
            for (i in 0..materia.horarios!!.length()-1){
                listaHorario[i] = (materia?.horarios?.optString(i, "") as String)
                listaAulas[i] = (materia?.aulas?.optString(i, "") as String)
            }
            horaLunes.text = "${listaHorario[0].orEmpty()} / ${listaAulas[0].orEmpty()}"
            horaMartes.text = "${listaHorario[1].orEmpty()} / ${listaAulas[1].orEmpty()}"
            horaMiercoles.text = "${listaHorario[2].orEmpty()} / ${listaAulas[2].orEmpty()}"
            horaJueves.text = "${listaHorario[3].orEmpty()} / ${listaAulas[3].orEmpty()}"
            horaViernes.text = "${listaHorario[4].orEmpty()} / ${listaAulas[4].orEmpty()}"
        }



        return miView
    }
}