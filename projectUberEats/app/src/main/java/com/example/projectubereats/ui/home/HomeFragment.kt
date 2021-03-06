package com.example.projectubereats.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.R
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.utils.Tools
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {

    private lateinit var url : String
    lateinit var recyclerNegocios: RecyclerView
    lateinit var editSearch: EditText
    lateinit var textFav: TextView
    lateinit var btnFav: ExtendedFloatingActionButton
    private var pressedFav: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnFav = view.findViewById(R.id.fabOnlyFav)
        textFav = view.findViewById(R.id.textFavoritesShow)
        recyclerNegocios = view.findViewById(R.id.recyclerCommerces)
        editSearch = view.findViewById(R.id.editSearch)

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {
                    refreshContacts()
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        val urlFav = "${resources.getString(R.string.api)}fav_comercios.php"
        btnFav.setOnClickListener {

            pressedFav = !pressedFav

            if (pressedFav){
                textFav.visibility = View.VISIBLE
                object : Tools(){
                    override fun formatResponse(response: String) {
                        try {
                            val json = JSONObject(response)
                            val output = json.getJSONArray("output")

                            val negocios = ArrayList<Commerce>()
                            for(i in 0..output.length()-1) {
                                val jsonCommerce = output.getJSONObject(i)
                                val negocio = Commerce(
                                    jsonCommerce.getInt("id_business"),
                                    jsonCommerce.getString("business"),
                                    jsonCommerce.getString("description"),
                                    jsonCommerce.getString("address"),
                                    jsonCommerce.getDouble("latitude"),
                                    jsonCommerce.getDouble("longitude"),
                                    jsonCommerce.getInt("id_category"),
                                    jsonCommerce.getString("category"),
                                    if(jsonCommerce.getInt("favorite")==1) true else false,
                                    jsonCommerce.getString("photo")
                                )
                                negocios.add(negocio)
                            }
                            recyclerNegocios.layoutManager = LinearLayoutManager(requireContext())
                            recyclerNegocios.adapter = CommerceAdapter(
                                requireContext(),
                                R.layout.recycler_row_commerce,
                                negocios
                            )

                        } catch (e: Exception) {
                            e.printStackTrace()
                            "Error, no hay negocios disponibles".toast(requireContext())
                        }
                    }
                }.consumeGet(requireContext(), urlFav)
            }else{
                textFav.visibility = View.INVISIBLE
                refreshContacts()
            }
        }

        refreshContacts()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun refreshContacts(){
        url = "${resources.getString(R.string.api)}comercios.php?filter=${editSearch.text}"

        object : Tools(){
            override fun formatResponse(response: String) {
                try {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    val negocios = ArrayList<Commerce>()
                    for(i in 0..output.length()-1) {
                        val jsonCommerce = output.getJSONObject(i)
                        val negocio = Commerce(
                            jsonCommerce.getInt("id_business"),
                            jsonCommerce.getString("business"),
                            jsonCommerce.getString("description"),
                            jsonCommerce.getString("address"),
                            jsonCommerce.getDouble("latitude"),
                            jsonCommerce.getDouble("longitude"),
                            jsonCommerce.getInt("id_category"),
                            jsonCommerce.getString("category"),
                            if(jsonCommerce.getInt("favorite")==1) true else false,
                            jsonCommerce.getString("photo")
                        )
                        negocios.add(negocio)
                    }
                    recyclerNegocios.layoutManager = LinearLayoutManager(requireContext())
                    recyclerNegocios.adapter = CommerceAdapter(
                        requireContext(),
                        R.layout.recycler_row_commerce,
                        negocios
                    )

                } catch (e: Exception) {
                    e.printStackTrace()
                    "Error, no hay negocios disponibles".toast(requireContext())
                }
            }
        }.consumeGet(requireContext(), url) //agregar params para la b??squeda



    }

    override fun onResume() {
        super.onResume()
        refreshContacts()
    }
}