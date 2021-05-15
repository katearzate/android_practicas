package com.example.plataformaescolarv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.plataformaescolarv2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var stringUsuario = intent.getStringExtra("usuario")

        binding.btnHomeCalificaciones.setOnClickListener {
            invokeActivity(CalificacionesActivity::class.java)
        }

        binding.btnHomeReticula.setOnClickListener {
            invokeActivity(ReticulaActivity::class.java)
        }

        binding.btnHomeDatosPersonales.setOnClickListener {
            val intent = Intent(this, DatosPersonalesActivity::class.java)
            intent.putExtra("usuario", stringUsuario)
            startActivityForResult(intent,1)
            finish()
        }

        binding.btnHomeSalir.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun invokeActivity(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivityForResult(intent,1)
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