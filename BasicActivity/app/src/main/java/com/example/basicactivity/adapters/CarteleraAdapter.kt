package com.example.basicactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.R
import com.example.basicactivity.myobjects.Movie

abstract class CarteleraAdapter(val context: Context, val movies: ArrayList<Movie>, val res: Int)
    : RecyclerView.Adapter<CarteleraAdapter.CarteleraVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarteleraVH {
        val vh = CarteleraVH(LayoutInflater.from(context).inflate(res, null))
        return vh
    }

    override fun onBindViewHolder(holder: CarteleraVH, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class CarteleraVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie : Movie) {
            val imgView = itemView.findViewById<ImageView>(R.id.recyclerCarteleraImg)
            val textView = itemView.findViewById<TextView>(R.id.recyclerCarteleraName)

            imgView.setImageResource(movie.img)
            textView.setText(movie.name)

            imgView.setOnClickListener { verHorarios(movie) }
        }
    }

    abstract fun verHorarios(movie : Movie)

}