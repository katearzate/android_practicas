package com.example.projectubereats.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.R
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.toast
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var url : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerNegocios = view.findViewById<RecyclerView>(R.id.recyclerCommerces)

        url = resources.getString(R.string.api)+"comercios.php"

        object : Tools(){
            override fun formatResponse(response: String) {
                Log.i("Comercios",response)
                try {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    val negocios = ArrayList<Commerce>()
                    for(i in 0..output.length()-1) {
                        val jsonCommerce = output.getJSONObject(i)
                        val negocio = Commerce(
                            jsonCommerce.getInt("id"),
                            jsonCommerce.getString("negocio"),
                            jsonCommerce.getString("descripcion"),
                            jsonCommerce.getString("direccion"),
                            jsonCommerce.getDouble("latitud"),
                            jsonCommerce.getDouble("longitud"),
                            jsonCommerce.getInt("id_categoria"),
                            jsonCommerce.getString("categoria"),
                            if(jsonCommerce.getInt("favorito")==1) true else false,
                            jsonCommerce.getString("foto")
                        )

                        negocios.add(negocio)
                    }

                    recyclerNegocios.adapter = CommerceAdapter(view.context, R.layout.recycler_row_commerce, negocios)
                    recyclerNegocios.layoutManager = LinearLayoutManager(view.context)

                } catch (e: Exception) {
                    e.printStackTrace()
                    "Error, no hay negocios disponibles".toast(view.context)
                }
            }
        }.consumeGet(view.context, url)
    }


}