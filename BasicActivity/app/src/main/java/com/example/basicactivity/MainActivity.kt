package com.example.basicactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.let {
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { //view ->     operador lambda omitido por it
            Snackbar.make(it, "¿Deseas salir?", Snackbar.LENGTH_LONG)
                    .setAction("si", {finish()}).show()
        }
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
            R.id.menuSettings -> Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
            R.id.menuExit -> finish()
            else -> true
        }
        return true
    }
}