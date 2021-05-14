package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.R
import org.json.JSONArray


class RecyclerReticula(val context: Context, val layout:Int, val kardex, val reticula:JSONArray) : RecyclerView.Adapter<RecyclerReticula.ReticulaVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReticulaVH {
        return ReticulaVH( LayoutInflater.from(context).inflate(layout, null) )
    }

    override fun onBindViewHolder(holder: ReticulaVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        reticula.length()
    }

    inner class ReticulaVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(i: Int) {
            val jsonSemestre = reticula.getJSONObject(i)

            val noSemestre = itemView.findViewById<TextView>(R.id.filaReticulaSemestre)
            noSemestre.text = "Semestre ${i+1}"

            val jsonArray = JSONArray()

            for (s in 0..kardex.length()-1){
                val jsonSemestre = kardex.getJSONObject(s)

                if(jsonSemestre.getInt("semestre") == (i+1)) {

                }
            }

            if(jsonArray.length() > 0){
                println("materias en semestre: ${i+1}: \n$jsonArray")

            }


        }
    }

}