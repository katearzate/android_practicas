package com.example.projectubereats.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.R
import com.example.projectubereats.adapters.OrdersAdapter
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.models.Location
import com.example.projectubereats.models.Order
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.dbGet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter
import org.json.JSONObject

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var url : String
    lateinit var recyclerOrders: RecyclerView
    private var lat = 0.0
    private var lng = 0.0

    private lateinit var currentMarker: Marker
    private var map: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val text: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            text.text = it
        })

        dashboardViewModel.getLat()?.observe(viewLifecycleOwner, { l ->
            l?.let {
                lat = it
                println("Lat received: $it")
            }
        })
        dashboardViewModel.getLng()?.observe(viewLifecycleOwner, { l ->
            l?.let {
                lng = it
                println("Lng received: $it")
            }
        })

        map = childFragmentManager.findFragmentById(R.id.map)
        recyclerOrders = root.findViewById(R.id.recyclerOrders)

        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        url = resources.getString(R.string.api)+"comercios.php"
        object : Tools(){
            override fun formatResponse(response: String) {
                Log.i("Comercios",response)
                try {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    val commercesList : ArrayList<LatLng> = arrayListOf()
                    map?.let {
                        for(i in 0..output.length()-1) {
                            val jsonCommerce = output.getJSONObject(i)
                            commercesList.add(
                                LatLng(
                                    jsonCommerce.getDouble("latitude"),
                                    jsonCommerce.getDouble("longitude")
                                )
                            )
                        }
                        setMarker(map as SupportMapFragment, commercesList)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    "No hay negocios disponibles".toast(view.context)
                }
            }
        }.consumeGet(view.context, url)

        requireContext().dbGet()?.let{ user ->
            val urlOrders = "${resources.getString(R.string.api)}ver_ordenes.php?id=${user.id}"

            object : Tools(){
                override fun formatResponse(response: String) {
                    try {
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")

                        val negocios = ArrayList<Order>()
                        for(i in 0..output.length()-1) {
                            val jsonCommerce = output.getJSONObject(i)
                            val negocio = Order(
                                jsonCommerce.getInt("id_business"),
                                jsonCommerce.getString("business"),
                                jsonCommerce.getString("address"),
                                jsonCommerce.getDouble("latitude"),
                                jsonCommerce.getDouble("longitude"),
                                jsonCommerce.getString("photo"),
                                jsonCommerce.getDouble("total")
                            )
                            negocios.add(negocio)
                        }
                        recyclerOrders.layoutManager = LinearLayoutManager(requireContext())
                        recyclerOrders.adapter = OrdersAdapter(
                            requireContext(),
                            R.layout.recycler_row_orders,
                            negocios
                        )

                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                        "ERROR. No hay ordenes".toast(requireContext())
                    }
                }
            }.consumeGet(requireContext(), urlOrders)
        }



    }

    //************************************ MAP **************************************
    private fun setMarker(map: SupportMapFragment, markers: ArrayList<LatLng>){
        val zoomLevel = 11f

        map.getMapAsync {
            val current = LatLng(lat, lng)
            currentMarker = it.addMarker(MarkerOptions().position(current).title("Current location"))

            for (i in 0 until markers.size) {
                it.addMarker(
                    MarkerOptions()
                        .position(markers.get(i))
                        .title("Negocio")
                )
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(markers.get(i), zoomLevel))
            }

            it.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomLevel))
        }
    }

}