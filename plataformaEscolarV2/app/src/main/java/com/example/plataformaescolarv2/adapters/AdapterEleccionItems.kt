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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaescolarv2.EleccionMateriasActivity
import com.example.plataformaescolarv2.R
import com.example.plataformaescolarv2.getters.Calificacion
import com.example.plataformaescolarv2.getters.ClasesDisponibles
import com.example.plataformaescolarv2.getters.Materia
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

abstract class AdapterEleccionItems(var context: Context, var materias: MutableList<ClasesDisponibles>)
    : RecyclerView.Adapter<AdapterEleccionItems.ViewHolder>(){

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewCalificacion = itemView.findViewById<TextView>(R.id.textViewCalificacionReticula)
        val textViewNombreMateria = itemView.findViewById<TextView>(R.id.textViewNombreMateriaReticula)
        val btnSeleccionarMateria = itemView.findViewById<Button>(R.id.btnSeleccionarMateria)
        val conjuntoItems = itemView.findViewById<LinearLayout>(R.id.Reticulabackground)

        init {

        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEleccionItems.ViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.lista_reticula, parent, false)
        return ViewHolder(itemHolder)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: AdapterEleccionItems.ViewHolder, position: Int) {
        var materia : ClasesDisponibles = materias.get(position)
        holder.textViewCalificacion.text = materia.materia!!.calificacion
        holder.textViewNombreMateria.text = materia.materia!!.nombreMateria

        holder.btnSeleccionarMateria.setOnClickListener {
            showPopup(materia, holder)
        }
        estadoMateria(holder)
    }

    override fun getItemCount(): Int = materias.size

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun showPopup(materia : ClasesDisponibles, holder: AdapterEleccionItems.ViewHolder){
        val inflater:LayoutInflater = LayoutInflater.from(context) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_seleccionar_materias,null)

        val popupWindow = PopupWindow(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,     //window width
                LinearLayout.LayoutParams.WRAP_CONTENT      //window height
        )

        popupWindow.width = 1000
        popupWindow.height = 1200

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
        //val btnMateriaElegida = view.findViewById<MaterialButton>(R.id.btnMateriaSeleccionableElegir)

        //********************************************crear funcion del btn creado btnMateriaElegida

        val nombreMateria = view.findViewById<TextView>(R.id.popupNombreMateria)
        nombreMateria.setText("Materia: ${materia.materia!!.nombreMateria}")

        val listaClases = view.findViewById<ListView>(R.id.listaPopupGrupos)
        listaClases.adapter = object : AdapterClases(
                context,
                R.layout.lista_materia_seleccionable,
                materia.clases!!,
                materia.materia?.creditos!!){
            override fun clickClase(materiaElegida: Materia) {
                materiaElegida.nombreMateria = materia.materia?.nombreMateria
                materiaElegida.creditos = materia.materia?.creditos
                clickClaseItem( materiaElegida)
                popupWindow.dismiss()

                if (materia.materia?.calificacion == "no cursada"){
                    materia.materia?.calificacion == "cursando"
                    holder.textViewCalificacion.text == "cursando"
                    estadoMateria(holder)
                }else if (materia.materia?.calificacion!!.toInt() < 70){
                    materia.materia?.calificacion == "reprobada"
                    holder.textViewCalificacion.text == "cursando"
                    estadoMateria(holder)
                }
            }
        }


        val btnCerrarPopup = view.findViewById<ExtendedFloatingActionButton>(R.id.btnCerrarPopup)
        btnCerrarPopup.isFocusable
        btnCerrarPopup.setOnClickListener{
            popupWindow.dismiss()
        }

        TransitionManager.beginDelayedTransition(view as ViewGroup?)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

    }

    private fun estadoMateria(holder: AdapterEleccionItems.ViewHolder){

        when(holder.textViewCalificacion.text.toString()){
            "cursando" -> {
                holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matCursando))
            }
            "no cursada" -> {
                holder.btnSeleccionarMateria.visibility = View.VISIBLE
                holder.textViewCalificacion.visibility = View.GONE
                holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matDisponible))
            }
            "reprobada" -> {
                holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matReprobada))
            }
            "ACA" -> {
                holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.extracurricular))
            }
            else -> {
                if(holder.textViewCalificacion.text.toString().toInt() < 70){
                    holder.btnSeleccionarMateria.visibility = View.VISIBLE
                    holder.textViewCalificacion.visibility = View.GONE
                    holder.conjuntoItems.setBackgroundColor(context.resources.getColor(R.color.matSinAcreditar))
                }
            }
        }
    }

    abstract fun clickClaseItem(materia: Materia)

}