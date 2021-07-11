package com.example.projectubereats.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.R
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.toast
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var url : String
    lateinit var recyclerNegocios: RecyclerView
    lateinit var editSearch: EditText
    lateinit var params: HashMap<String, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = resources.getString(R.string.api)+"comercios.php"

        recyclerNegocios = view.findViewById(R.id.recyclerCommerces)
        editSearch = view.findViewById(R.id.editSearch)

        if (editSearch.text.toString() == ""){
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
                        recyclerNegocios.layoutManager = LinearLayoutManager(view.context)
                        recyclerNegocios.adapter = CommerceAdapter(view.context, R.layout.recycler_row_commerce, negocios)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        "Error, no hay negocios disponibles".toast(view.context)
                    }
                }
            }.consumeGet(view.context, url)
        }

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {
                    params.clear()
                    params.put("filter", editSearch.text.toString())
                    refreshContacts(params)
                }
            }

            override fun afterTextChanged(s: Editable?) { }
        })

    }

    fun refreshContacts(params: HashMap<String,String>){
        val negocios = ArrayList<Commerce>()
        try {
            object : Tools(){
                override fun formatResponse(response: String) {
                    Log.i("Comercios",response)
                    try {
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")

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

                    } catch (e: Exception) {
                        e.printStackTrace()
                        "Error, no hay negocios disponibles".toast(requireContext())
                    }
                }
            }.consumeGet(requireContext(), url, params)

            recyclerNegocios.layoutManager = LinearLayoutManager(requireContext())
            recyclerNegocios.adapter = CommerceAdapter(
                requireContext(),
                R.layout.recycler_row_commerce,
                negocios)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}