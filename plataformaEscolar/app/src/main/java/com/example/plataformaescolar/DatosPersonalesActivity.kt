package com.example.plataformaescolar

import android.content.Intent
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
        val jsonUsuarioEdit = jsonUsuario
        println(jsonUsuario)

        binding.numeroControlDP.setText(jsonUsuario.getString("noControl"))
        binding.nombreDP.setText(jsonUsuario.getString("nombre"))
        binding.carreraDP.setText(jsonUsuario.getString("carrera"))
        binding.semestreDP.setText(jsonUsuario.getString("semestre"))
        binding.contrasenaDP.setText(jsonUsuario.getString("contrasena"))

        binding.btnActualizar.setOnClickListener {
            jsonUsuarioEdit.put("nombre", binding.nombreDP.text.toString())
            jsonUsuarioEdit.put("noControl", binding.numeroControlDP.text.toString())
            jsonUsuarioEdit.put("carrera", binding.carreraDP.text.toString())
            jsonUsuarioEdit.put("semestre", binding.semestreDP.text.toString())
            jsonUsuarioEdit.put("contrasena", binding.contrasenaDP.text.toString())
            println(jsonUsuarioEdit)
        }

        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("usuario", jsonUsuarioEdit.toString())
            startActivity(intent)
            finish()
        }

        //val usuario1 = Usuario("Katherine","18121600", "TICS","6","123")
        //val jsonString = Gson().toJson(usuario1)
/*
        var jsonElement : JsonObject = JsonParser.parseString(Usuario.json).asJsonObject
        binding.numeroControlDP.text = "" + jsonElement.get("noControl").asString
        binding.nombreDP.text = "" + jsonElement.get("nombre").asString
  */
    }
}


