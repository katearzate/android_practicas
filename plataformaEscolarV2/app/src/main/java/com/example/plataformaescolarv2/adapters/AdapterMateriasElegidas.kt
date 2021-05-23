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

        val creditos : TextView = miView.findViewById(R.id.materiaElegidaCreditos)
        val nomMateria : TextView = miView.findViewById(R.id.materiaElegidaNombre)
        val profesor : TextView = miView.findViewById(R.id.materiaElegidaProfesor)
        val grupo : TextView = miView.findViewById(R.id.materiaElegidaGrupo)
        val horaLunes : TextView = miView.findViewById(R.id.materiaElegidaLunes)
        val horaMartes : TextView = miView.findViewById(R.id.materiaElegidaMartes)
        val horaMiercoles : TextView = miView.findViewById(R.id.materiaElegidaMiercoles)
        val horaJueves : TextView = miView.findViewById(R.id.materiaElegidaJueves)
        val horaViernes : TextView = miView.findViewById(R.id.materiaElegidaViernes)

        val materia : Materia = listaMaterias.get(position)

        creditos.text = materia.creditos.toString()
        nomMateria.text = materia.nombreMateria
        profesor.text = materia.profesor
        grupo.text = materia.grupo

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