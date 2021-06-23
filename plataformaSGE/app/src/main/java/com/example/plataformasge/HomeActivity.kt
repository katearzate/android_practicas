package com.example.plataformasge

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.plataformasge.databinding.ActivityHomeBinding
import com.example.plataformasge.models.Subject
import com.example.plataformasge.models.User
import com.example.plataformasge.models.ViewModelPersonalData
import com.example.plataformasge.models.ViewModelSchedule

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navigationController : NavController
    private val viewModelPD: ViewModelPersonalData by viewModels()
    private val viewModelS: ViewModelSchedule by viewModels()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = "SGE"
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        var user = intent.getParcelableExtra<User>("user")
        viewModelPD.setUser(user!!)

        navigationController = findNavController(R.id.main_container)
        setupActionBarWithNavController(
            navigationController, AppBarConfiguration(
                setOf(
                    R.id.homeFragment,
                    R.id.scheduleFragment,
                    R.id.kardexFragment,
                    R.id.reticulaFragment,
                    R.id.personalDataFragment
                )
            )
        )

        binding.menuBottomNavigation.setupWithNavController(navigationController)
        binding.menuBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuInicio -> {
                    navigationController.navigate(R.id.homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuSchedule -> {
                    navigationController.navigate(R.id.scheduleFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuKardex -> {
                    navigationController.navigate(R.id.kardexFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuReticula -> {
                    navigationController.navigate(R.id.reticulaFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuPersonalData -> {
                    navigationController.navigate(R.id.personalDataFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Inflate menu in any fragment
        when(item.itemId) {
            R.id.menuSalir -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}