package com.example.basicactivity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.basicactivity.myobjects.Utils.Companion.toast

class MainActivity : AppCompatActivity() {

    lateinit var topNav : Toolbar
    lateinit var bottomNav : BottomNavigationView

    private lateinit var navigationController : NavController

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topNav = findViewById(R.id.toolbar)

        setSupportActionBar(topNav)

        bottomNav = findViewById(R.id.bottomNavigationView)

        supportActionBar?.let {
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }



        bottomNav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.menuCartelera -> {
                        topNav.title = "Cartelera"
                        val carteleraFrag =  FirstFragment()
                        openFragment(carteleraFrag)
                        return true
                    }
                    R.id.menuAlimentos -> {
                        topNav.title = "Alimentos"
                        return true
                    }
                    R.id.menuPerfil -> {
                        topNav.title = "Perfil"
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun openFragment(fragment: Fragment){
        val transaccion = supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.container, fragment)
        transaccion.addToBackStack(null)
        transaccion.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //con esto no importa en que fragmento se encuentre, se puede salir en cualquier momento
        when(item.itemId) {
            R.id.menuSettings -> "Settings".toast(this)
            R.id.menuExit -> finish()
            else -> true
        }
        return super.onOptionsItemSelected(item)
    }
}