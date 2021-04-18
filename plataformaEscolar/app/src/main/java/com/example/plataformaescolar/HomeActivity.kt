package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.plataformaescolar.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var stringUsuario = intent.getStringExtra("usuario")
        //println("*** USUARIO: ")
        //println(stringUsuario)

        binding.calificaciones.setOnClickListener {
            val intent = Intent(this, CalificacionesActivity::class.java)
            startActivity(intent)
        }

        binding.horario.setOnClickListener {
            val intent = Intent(this, HorarioActivity::class.java)
            startActivity(intent)
        }

        binding.reticula.setOnClickListener {
            val intent = Intent(this, ReticulaActivity::class.java)
            startActivity(intent)
        }

        binding.datosPersonales.setOnClickListener {
            val intent = Intent(this, DatosPersonalesActivity::class.java)
            intent.putExtra("usuario", stringUsuario)
            startActivity(intent)
        }

        binding.salir.setOnClickListener {
            //si cierra sesion, implementar los cambios en datos personales
            intent.putExtra("usuario", stringUsuario)
            val intent = Intent(this, MainActivity::class.java)
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