package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.listas.clases.MiAdapter
import com.example.listas.clases.Producto

class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        val lista = ArrayList<Producto>()
        val producto = Producto("Papas", 15.5, 3, R.mipmap.papas)
        lista.add(producto)

        val producto2 = Producto("Refresco", 13.4, 7, R.mipmap.drpepper)
        lista.add(producto2)

        val producto3 = Producto("Pizza", 4.8, 10, R.mipmap.pizza)
        lista.add(producto3)

        listView.adapter = MiAdapter(this, R.layout.list_row,lista)
    }
}