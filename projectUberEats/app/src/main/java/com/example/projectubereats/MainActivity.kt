package com.example.projectubereats

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.example.projectubereats.databinding.ActivityMainBinding
import com.example.projectubereats.models.User
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.dbGet
import com.example.projectubereats.utils.Tools.Companion.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationManager: LocationManager

    private var lat = 0.0
    private var lng = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        supportActionBar?.hide()

        dbGet()?.let {
            myLocation()
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            intent.putExtra("user", it)
            intent.putExtra("lat", lat)
            intent.putExtra("lng", lng)
            startActivity(intent)
            finish()
        }

        binding.btnAccess.setOnClickListener {
            var correcto = true
            if(binding.editUsr.text.isEmpty()) {
                binding.editUsr.setError("El usuario no debe ser vacío")
                correcto = false
            }
            if(!binding.editUsr.text.contains("@") || !binding.editUsr.text.contains(".")
                || binding.editUsr.text.length < 5) {
                binding.editUsr.error = "El correo no es válido"
                correcto = false
            }
            if(binding.editPass.text.isEmpty()) {
                binding.editPass.setError("La contraseña no debe ser vacía")
                correcto = false
            }
            if(binding.editPass.text.length < 2) {
                binding.editPass.error = "La contraseña es muy corta"
                correcto = false
            }
            if(correcto) {
                myLocation()
                login(binding.editUsr.text.toString(), binding.editPass.text.toString())
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                "Permiso otorgado".toast(this)
            } else {
                cannotContinue()
            }
        }
    }

    private fun myLocation() {
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {
            if (hasGps) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, object:
                    LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        p0?.let {
                            lat = it.latitude
                            lng = it.longitude
                            //println("Lat: $lat y Lng: $lng")
                        }
                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

                    override fun onProviderEnabled(provider: String) {}

                    override fun onProviderDisabled(provider: String) {}
                })
                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                localGpsLocation?.let {
                    lat = it.latitude
                    lng = it.longitude
                    //println("Lat: $lat y Lng: $lng")
                }
            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0F, object:
                    LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        p0?.let {
                            lat = it.latitude
                            lng = it.longitude
                            //println("Lat: $lat y Lng: $lng")
                        }
                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

                    override fun onProviderEnabled(provider: String) {}

                    override fun onProviderDisabled(provider: String) {}
                })
                val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                localNetworkLocation?.let {
                    lat = it.latitude
                    lng = it.longitude
                    //println("Lat: $lat y Lng: $lng")
                }
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }

    }

    private fun cannotContinue() {
        MotionToast.createToast(
            this,
            "Error ☹️",
            "¡No puedo funcionar sin la ubicación!",
            MotionToast.TOAST_ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(this,R.font.helvetica_regular)
        )
        "No puedo funcionar sin la ubicación".toast(this)
        finish()
    }

    private fun login(usr: String, pass: String) {
        val url = "${resources.getString(R.string.api)}encuentra.php"
        Log.d("URL",url)

        val params = HashMap<String,String?>()
        params.put("usr", usr)
        params.put("pass", pass)

        object : Tools() {
            override fun formatResponse(response: String) {
                Log.i("Consume", response)
                try {
                    val respon2 = response.replaceFirst("Array", "", true)
                    val json = JSONObject(respon2)
                    val output = json.getJSONArray("output")

                    val gson = Gson()
                    val usuario = gson.fromJson(output.getJSONObject(0).toString(), User::class.java)

                    // RememberMe
                    if(binding.switchRemember.isChecked) {
                        this@MainActivity.dbSet(usuario)
                    }

                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    intent.putExtra("user", usuario)
                    intent.putExtra("lat", lat)
                    intent.putExtra("lng", lng)
                    startActivity(intent)
                    finish()

                } catch (e: Exception) {
                    Log.e("FR", "Error:\n$e")
                    MotionToast.createToast(
                        this@MainActivity,
                        "Error ☹️",
                        "No se encuentra el usuario",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(this@MainActivity,R.font.helvetica_regular)
                    )
                    "No se pudo conectar, intente mas tarde".toast(this@MainActivity)
                }
            }
        }.consumePost(this, url, params)
    }

}