package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.plataformaescolar.clases.Usuario
import com.example.plataformaescolar.databinding.ActivitySignInBinding
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.ArrayList

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private var noSemeste = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stringBD = intent.getStringExtra("BD")
        var jsonBD = JSONObject(stringBD)

        //*********generador de numero de semestre**********
        val semestre = ArrayList<String>()
        for(i in 1..12) {
            semestre.add("$i")
        }
        binding.semestre.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, semestre)
        binding.semestre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p1?.let {
                    val textView = it.findViewById<TextView>(android.R.id.text1)
                    noSemeste = textView.text.toString()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }


        binding.btnSignIn.setOnClickListener {
            /*
            //objeto de Nombre
            var usuario1 = Usuario(binding.nombre.text.toString(), binding.noControl.text.toString(), binding.carrera.text.toString(),
                    binding.semestre.text.toString(), binding.contrasena.text.toString())

            //crear objeto gson
            var gson = Gson()
            var jsonString: String = gson.toJson(usuario1)
            val file= File(String())
            file.writeText(jsonString)
            */
            val json = JSONObject()
            json.put("nombre", binding.nombre.text.toString())
            json.put("noControl", binding.noControl.text.toString())
            json.put("carrera", binding.carrera.text.toString())
            json.put("semestre", noSemeste.toInt())
            json.put("contrasena", binding.contrasena.text.toString())

            val usuarios = jsonBD.getJSONArray("usuarios")
            usuarios.put(json)

            jsonBD = JSONObject()
            jsonBD.put("usuarios", usuarios)

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("usuario", json.toString())
            intent.putExtra("BD", jsonBD.toString())
            startActivity(intent)
            finish()
        }
    }

}