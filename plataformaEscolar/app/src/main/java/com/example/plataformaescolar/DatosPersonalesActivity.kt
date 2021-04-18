package com.example.plataformaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.clases.Usuario
import com.example.plataformaescolar.databinding.ActivityDatosPersonalesBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject

class DatosPersonalesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatosPersonalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPersonalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var usuario = intent.getStringExtra("usuario")
        val jsonUsuario = JSONObject(usuario)

        println(usuario)

        binding.numeroControlDP.setText(jsonUsuario.getString("noControl"))
        binding.nombreDP.setText(jsonUsuario.getString("nombre"))
        binding.carreraDP.setText(jsonUsuario.getString("carrera"))
        binding.semestreDP.setText(jsonUsuario.getString("semestre"))
        binding.contrasenaDP.setText(jsonUsuario.getString("contrasena"))

        binding.btnActualizar.setOnClickListener {
            /*val json = JSONObject()
            json.put("nombre", binding.nombreDP.text.toString())
            json.put("noControl", binding.numeroControlDP.text.toString())
            json.put("carrera", binding.carreraDP.text.toString())
            json.put("semestre", binding.semestreDP.text.toString().toInt())
            json.put("contrasena", binding.contrasenaDP.text.toString())*/

            
        }

        //val usuario1 = Usuario("Katherine","18121600", "TICS","6","123")
        //val jsonString = Gson().toJson(usuario1)
/*
        var jsonElement : JsonObject = JsonParser.parseString(Usuario.json).asJsonObject
        binding.numeroControlDP.text = "" + jsonElement.get("noControl").asString
        binding.nombreDP.text = "" + jsonElement.get("nombre").asString
        binding.carreraDP.text = "" + jsonElement.get("carrera").asString
        binding.semestreDP.text = "" + jsonElement.get("semestre").asString
        binding.contrasenaDP.text = "" + jsonElement.get("contrasena").asString
  */
    }
}