package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.Usuario
import com.example.plataformaescolar.databinding.ActivityDatosPersonalesBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class DatosPersonalesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatosPersonalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPersonalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jsonElement : JsonObject = JsonParser.parseString(Usuario.json).asJsonObject

        //val usuario1 = Usuario("Katherine","18121600", "TICS","6","123")
        //val jsonString = Gson().toJson(usuario1)

        binding.numeroControlDP.text = "" + jsonElement.get("noControl").asString
        binding.nombreDP.text = "" + jsonElement.get("nombre").asString
        binding.carreraDP.text = "" + jsonElement.get("carrera").asString
        binding.semestreDP.text = "" + jsonElement.get("semestre").asString
        binding.contrasenaDP.text = "" + jsonElement.get("contrasena").asString
    }
}