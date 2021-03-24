package com.example.listas.clases

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.listas.R

//intermediario entre fuentes de datos e interfaz

class MiAdapter (val context:Context, val layout:Int, val lista:ArrayList<Producto>) : BaseAdapter (){
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

    //recurso de interfaz; regresa la vista inflada -> si hay 10 elementos, 10 veces se ejecuta
    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val imagen = miView.findViewById<ImageView>(R.id.imageViewRow)
        val textViewNombre = miView.findViewById<TextView>(R.id.textViewNombreProductoRow)
        val textViewPrecio = miView.findViewById<TextView>(R.id.textViewNombrePrecioRow)
        val textViewCantidad = miView.findViewById<TextView>(R.id.textViewNombreCantidadRow)
        val btnComprar = miView.findViewById<Button>(R.id.btnComprarRow)

        textViewNombre.text = lista[index].nombre
        textViewPrecio.text = "\$${lista[index].precio}"
        textViewCantidad.text = "${lista[index].cantidad}"

        imagen.setImageResource(lista[index].imagen)

        btnComprar.setOnClickListener {
            val alertDialog = AlertDialog.Builder(miView.context)
            alertDialog.setTitle("Producto Comprado")
            alertDialog.setMessage("Gracias por comprar ${lista[index].nombre}")
            alertDialog.show()
        }

        return miView
    }

}