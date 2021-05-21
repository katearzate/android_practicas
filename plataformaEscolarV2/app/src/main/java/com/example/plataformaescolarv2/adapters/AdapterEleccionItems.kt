package com.example.plataformaescolarv2.adapters

import android.content.Context
import android.os.Build
import android.graphics.Color
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.EleccionMateriasActivity
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Calificacion
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class AdapterEleccionItems(var context: Context, var materias: MutableList<Calificacion>)
    : RecyclerView.Adapter<AdapterEleccionItems.ViewHolder>(){

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewCalificacion = itemView.findViewById<TextView>(R.id.textViewCalificacionReticula)
        val textViewNombreMateria = itemView.findViewById<TextView>(R.id.textViewNombreMateriaReticula)
        val btnSeleccionarMateria = itemView.findViewById<Button>(R.id.btnSeleccionarMateria)
        val conjuntoItems = itemView.findViewById<LinearLayout>(R.id.Reticulabackground)

        init {
            btnSeleccionarMateria.setOnClickListener {
                val inflater:LayoutInflater = LayoutInflater.from(context) as LayoutInflater
                val view = inflater.inflate(R.layout.popup_seleccionar_materias,null)

                val popupWindow = PopupWindow(
                        view,
                        LinearLayout.LayoutParams.WRAP_CONTENT,     //window width
                        LinearLayout.LayoutParams.WRAP_CONTENT      //window height
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    popupWindow.elevation = 20.0F
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    val slideIn = Slide()
                    slideIn.slideEdge = Gravity.TOP
                    popupWindow.enterTransition = slideIn

                    val slideOut = Slide()
                    slideOut.slideEdge = Gravity.BOTTOM
                    popupWindow.exitTransition = slideOut
                }

                //Funcionalidad de items del popup

                val btnCerrarPopup = view.findViewById<ExtendedFloatingActionButton>(R.id.btnCerrarPopup)
                btnCerrarPopup.setOnClickListener{
                    popupWindow.dismiss()
                }

                TransitionManager.beginDelayedTransition(view as ViewGroup?)
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEleccionItems.ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.lista_reticula, parent, false)
        return ViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: AdapterEleccionItems.ViewHolder, position: Int) {
        var materia : Calificacion = materias.get(position)
        holder.textViewCalificacion.text = materia.calificacion
        holder.textViewNombreMateria.text = materia.nomMateriaCalificacion

        if(holder.textViewCalificacion.text.toString() == "no cursada"){
            holder.btnSeleccionarMateria.visibility = View.VISIBLE
            holder.textViewCalificacion.visibility = View.GONE
            holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matDisponible))
        }else if(holder.textViewCalificacion.text.toString() == "ACA") {
            holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.extracurricular))
        }else if(holder.textViewCalificacion.text.toString() == "reprobada") {
            holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matReprobada))
        }else if(holder.textViewCalificacion.text.toString() == "cursando") {
            holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matCursando))
        }else if(holder.textViewCalificacion.text.toString().toInt() < 70) {
            holder.btnSeleccionarMateria.visibility = View.VISIBLE
            holder.textViewCalificacion.visibility = View.GONE
            holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matSinAcreditar))
        }
    }

    override fun getItemCount(): Int = materias.size



}