package com.example.basicactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.adapters.CompraRecyclerAdapter
import com.example.basicactivity.myobjects.DatabaseManager

class PerfilFragment : Fragment(R.layout.fragment_perfil){
    lateinit var recyclerCompras : RecyclerView
    lateinit var dbManager : DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.let {
            recyclerCompras = it.findViewById(R.id.recyclerCompras)
            dbManager = DatabaseManager(requireActivity(), "DBpeliculas", null, 1)

            val compras = dbManager.show()
            recyclerCompras.adapter = CompraRecyclerAdapter(requireActivity(), R.layout.recycler_compras, compras)
            recyclerCompras.layoutManager = LinearLayoutManager(requireActivity())

        }

        return view
    }

}