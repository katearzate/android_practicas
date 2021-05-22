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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.basicactivity.myobjects.Utils.Companion.toast

class MainActivity : AppCompatActivity() {

    lateinit var topNav : Toolbar
    lateinit var bottomNav : BottomNavigationView

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

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val snack = Snackbar.make(it, "¿Deseas salir?", Snackbar.LENGTH_LONG)
            snack.setAction("Si", { finish() })
            snack.setActionTextColor(Color.RED)
            val layout = snack.view.layoutParams as CoordinatorLayout.LayoutParams
            layout.gravity = Gravity.TOP
            snack.view.layoutParams = layout
            snack.show()
        }

        bottomNav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                return true
            }
        })
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