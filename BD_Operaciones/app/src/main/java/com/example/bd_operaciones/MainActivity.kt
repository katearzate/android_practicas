package com.example.bd_operaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bd_operaciones.adapters.ProductsAdapter
import com.example.bd_operaciones.models.DataBaseManager
import com.example.bd_operaciones.models.Product
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var editProduct : EditText
    lateinit var btnAdd : ExtendedFloatingActionButton
    lateinit var recyclerProducts : RecyclerView

    lateinit var dbManager : DataBaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editProduct = findViewById(R.id.editNewProduct)
        btnAdd = findViewById(R.id.btnAdd)
        recyclerProducts = findViewById(R.id.recyclerProduct)

        dbManager = DataBaseManager(this, "myFirstDB", null, 1)

        btnAdd.setOnClickListener{
            val nameProduct = editProduct.text
            val product = Product(0, nameProduct.toString())
            editProduct.setText("")
            try {
                dbManager.add(product)
                "Producto agregado".toast()
                updateProduct()
            }catch (e: Exception){
                e.printStackTrace()
                "Error al agregar!".toast()
            }
        }
        updateProduct()
    }

    private fun updateProduct() {
        try {
            val products = dbManager.show()
            recyclerProducts.adapter = object : ProductsAdapter(this, R.layout.recycler_row_products, products){
                override fun modify(old: Product, new: Product) {
                    dbManager.edit(old, new)
                    updateProduct()
                }

                override fun delete(product: Product) {
                    dbManager.delete(product)
                    updateProduct()
                }
            }
            recyclerProducts.layoutManager = LinearLayoutManager(this)
        } catch (e:Exception){
            e.printStackTrace()
            "Error al mostrar los productos!".toast()
        }
    }

    fun String.toast(){
        Toast.makeText(this@MainActivity, this, Toast.LENGTH_LONG).show()
    }
}