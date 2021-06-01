package com.example.plataformasge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.menuSalir -> finish()  //mandar a login
                else -> true
            }
            return super.onOptionsItemSelected(item)
        }
        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }
    }
}