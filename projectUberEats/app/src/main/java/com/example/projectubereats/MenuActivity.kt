package com.example.projectubereats

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectubereats.databinding.ActivityMenuBinding
import com.example.projectubereats.models.User
import com.example.projectubereats.ui.dashboard.DashboardViewModel
import com.example.projectubereats.utils.Tools.Companion.dbRemove
import com.example.projectubereats.utils.Tools.Companion.toast
import www.sanju.motiontoast.MotionToast

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var viewModelDash: DashboardViewModel

    private lateinit var user: User
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as User
        lat = intent.getDoubleExtra("lat", 0.0)
        lng = intent.getDoubleExtra("lng", 0.0)

        if(lat == 0.0 && lng == 0.0) {
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
            println("No puedo funcionar sin la ubicación")
            finish()
        }

        viewModelDash = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModelDash.setLat(lat)
        viewModelDash.setLng(lng)

        MotionToast.createToast(
            this,
            "Bienvenido",
            "¿Qué te apetece hoy ${user.name}?",
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(this,R.font.helvetica_regular)
        )

        supportActionBar?.let {
            it.title = "Uberri Eats"
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_home -> {
                    navController.navigate(R.id.navigation_home)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_dashboard -> {
                    navController.navigate(R.id.navigation_dashboard)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_notifications -> {
                    navController.navigate(R.id.navigation_notifications)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuLogout) {
            this.dbRemove()
            finish()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if(item.itemId == R.id.menuExit) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}