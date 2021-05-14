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


        binding.btnHomeDatosPersonales.setOnClickListener {
            val intent = Intent(this, DatosPersonalesActivity::class.java)
            intent.putExtra("usuario", stringUsuario)
            startActivity(intent)
            finish()
        }

        binding.btnHomeSalir.setOnClickListener {
            intent.putExtra("usuario", stringUsuario)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
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