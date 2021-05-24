package com.example.basicactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.R
import com.example.basicactivity.myobjects.Compra

class CompraRecyclerAdapter(val context: Context, val result: Int, val compras: ArrayList<Compra>) :
RecyclerView.Adapter<CompraRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var img : ImageView = itemView.findViewById(R.id.recyclerCompraImg)
        val pelicula : TextView = itemView.findViewById(R.id.recyclerCompraNombrePelicula)
        val hora : TextView = itemView.findViewById(R.id.recyclerCompraHora)
        val noBoletos : TextView = itemView.findViewById(R.id.recyclerCompraNoBoletos)
        val total : TextView = itemView.findViewById(R.id.recyclerCompraTotal)
        

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.recycler_compras, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var compra : Compra = compras.get(position)
        holder.img = compra.img as ImageView
        holder.pelicula.text = compra.pelicula
        holder.hora.text = compra.horario
        holder.noBoletos.text = compra.cantidad.toString()
        holder.total.text = compra.total.toString()
    }

    override fun getItemCount(): Int = compras.size

}