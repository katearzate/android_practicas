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
import com.example.projectubereats.R
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.models.Location
import com.example.projectubereats.utils.Tools
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var url : String
    private var lat = 0.0
    private var lng = 0.0

    private lateinit var currentMarker: Marker
    private lateinit var businessMarker: Marker

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


        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commercesList : ArrayList<Location> = arrayListOf()
        url = resources.getString(R.string.api)+"comercios.php"
        object : Tools(){
            override fun formatResponse(response: String) {
                Log.i("Comercios",response)
                try {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    for(i in 0..output.length()-1) {
                        val jsonCommerce = output.getJSONObject(i)
                        commercesList.add(
                            Location(
                            jsonCommerce.getDouble("latitude"),
                            jsonCommerce.getDouble("longitude")
                        )
                        )

                        println("NUMERO ${i}: ${commercesList.get(i)}")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    "No hay negocios disponibles".toast(view.context)
                }
            }
        }.consumeGet(view.context, url)

        //El problema es acceder al arreglo de elementos con sus ubicaciones...

        //************************************ MAP *************************************+
        childFragmentManager.findFragmentById(R.id.map)?.let {
            val map = it as SupportMapFragment
            val zoomLevel = 13f

            map.getMapAsync {
                val current = LatLng(lat, lng)
                currentMarker = it.addMarker(MarkerOptions().position(current).title("Current location"))
                //businessMarker = it.addMarker(MarkerOptions().position(commercesList.get(0)).title("Business"))

                it.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomLevel))
            }
        }

        /*
        childFragmentManager.findFragmentById(R.id.map)?.let{
            val map = it as SupportMapFragment
            val zoomLevel = 8f

            map.getMapAsync {
                val current = LatLng(lat, lng)

                it.addMarker(MarkerOptions().position(current).title("Current location"))
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomLevel))


                it.addMarker(MarkerOptions().position(commercesList.get(1)).title("Business"))

                for (i in 0 until commercesList.size) {
                    it.addMarker(
                        MarkerOptions()
                            .position(commercesList.get(i))
                            .title("Marker")
                    )
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(commercesList.get(i), zoomLevel))
                }


            }
        }*/
    }

}