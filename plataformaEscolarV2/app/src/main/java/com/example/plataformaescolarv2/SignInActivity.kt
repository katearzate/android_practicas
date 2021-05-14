package com.example.plataformaescolarv2

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.plataformaescolarv2.databinding.ActivitySignInBinding
import org.json.JSONObject
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
        binding.semestre.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, semestre)
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

            if(binding.nombre.text.isNotEmpty() && binding.contrasena.text.isNotEmpty()
                && binding.carrera.text.isNotEmpty() && binding.noControl.text.isNotEmpty()){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("usuario", json.toString())
                intent.putExtra("BD", jsonBD.toString())
                startActivity(intent)
                finish()
            }
            else{
                showAlert()
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("¡No se ha completado todos los campos!")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}