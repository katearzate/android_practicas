package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.Usuario
import com.example.plataformaescolar.databinding.ActivitySignInBinding
import com.google.gson.Gson
import org.json.JSONArray
import java.io.File
import java.util.ArrayList

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {

            //objeto de Nombre
            var usuario1 = Usuario(binding.nombre.text.toString(), binding.noControl.text.toString(), binding.carrera.text.toString(),
                    binding.semestre.text.toString(), binding.contrasena.text.toString())

            //crear objeto gson
            var gson = Gson()
            var jsonString: String = gson.toJson(usuario1)
            val file= File(String())
            file.writeText(jsonString)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}