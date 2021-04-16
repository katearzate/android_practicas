package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformaescolar.clases.Usuario
import com.example.plataformaescolar.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.File
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jsonElement : JsonObject = JsonParser.parseString(Usuario.json).asJsonObject
/*
        var gson = Gson()

        val bufferedReader: BufferedReader = File(String()).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        var usuario = gson.fromJson(inputString, SignInActivity::class.java)
*/

        binding.btnLogin.setOnClickListener {
            if((binding.contrasenaLogin.text.toString() == jsonElement.get("contrasena").asString) &&
                (binding.noControlLogin.text.toString() == jsonElement.get("noControl").asString)){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                showAlert()
            }
        }

        binding.redirigirSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    //Muestra alerta si el numero de control o la contraseña ingresada son erroneas
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Numero de control o contraseña incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}