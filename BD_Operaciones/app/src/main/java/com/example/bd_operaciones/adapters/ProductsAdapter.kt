package com.example.bd_operaciones.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bd_operaciones.R
import com.example.bd_operaciones.models.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

abstract class ProductsAdapter(val context: Context, val result: Int, val products: ArrayList<Product>)
    : RecyclerView.Adapter<ProductsAdapter.ProductsVH>() {
    inner class ProductsVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(product: Product){
            val textProduct : TextView = itemView.findViewById(R.id.textViewNameProduct)
            val btnEdit : FloatingActionButton = itemView.findViewById(R.id.btnEditProduct)
            val btnDelete : FloatingActionButton = itemView.findViewById(R.id.btnDeleteProduct)

            textProduct.text = product.details

            btnEdit.setOnClickListener {
                val alert = AlertDialog.Builder(itemView.context)
                alert.setTitle("Producto modificado")
                alert.setMessage("Ingresa el nuevo nombre para ${product.details}")

                val editText = EditText(itemView.context)
                alert.setView(editText)

                alert.setNegativeButton("Cancelar", {dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                })
                alert.setPositiveButton("Guardar", {dialogInterface: DialogInterface, i: Int ->
                    val new = Product(product.id, editText.text.toString())
                    modify(product, new)

                    dialogInterface.dismiss()
                })
                alert.show()
            }

            btnDelete.setOnClickListener {
                val alert = AlertDialog.Builder(itemView.context)
                alert.setTitle("Confirmacion")
                alert.setMessage("¿Estas seguro que deseas eliminar a ${product.details}?")

                alert.setNegativeButton("Cancelar", {dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                })
                alert.setPositiveButton("Continuar", {dialogInterface: DialogInterface, i: Int ->
                    delete(product)

                    dialogInterface.dismiss()
                })
                alert.show()
            }
        }
    }

    abstract fun modify(old: Product, new: Product)

    abstract fun delete(product: Product)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsVH {
        val view = LayoutInflater.from(context).inflate(result, null)
        return ProductsVH(view)
    }

    override fun onBindViewHolder(holder: ProductsVH, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}