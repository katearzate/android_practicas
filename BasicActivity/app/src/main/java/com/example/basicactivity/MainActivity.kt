package com.example.basicactivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.basicactivity.myobjects.DatabaseManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.basicactivity.myobjects.Utils.Companion.toast
import com.google.android.material.bottomappbar.BottomAppBar

class MainActivity : AppCompatActivity() {

    lateinit var topNav : Toolbar
    lateinit var bottomNav : BottomNavigationView
    lateinit var bottomBar : BottomAppBar

    private lateinit var navigationController : NavController
    lateinit var dbManager : DatabaseManager

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topNav = findViewById(R.id.toolbar)
        setSupportActionBar(topNav)
        supportActionBar?.let {
            it.title = "Cineponys"
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        bottomNav = findViewById(R.id.bottomNavigationView)
        navigationController = findNavController(R.id.nav_main_container)

        setupActionBarWithNavController(navigationController, AppBarConfiguration(setOf(
            R.id.CarteleraFragment, R.id.alimentosFragment, R.id.perfilFragment
        )))

        bottomNav.setupWithNavController(navigationController)
        bottomNav.background = null

        bottomBar = findViewById(R.id.bottomAppBar)
        bottomBar.background = null

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuCartelera -> {
                    //topNav.title = "Cartelera"
                    navigationController.navigate(R.id.CarteleraFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuAlimentos -> {
                    //topNav.title = "Alimentos"
                    navigationController.navigate(R.id.alimentosFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuPerfil -> {
                    //topNav.title = "Perfil"
                    navigationController.navigate(R.id.perfilFragment)
                    dbManager = DatabaseManager(this, "DBpeliculas", null, 1)
                    dbManager.show()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        bottomNav.selectedItemId = R.id.menuCartelera
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}