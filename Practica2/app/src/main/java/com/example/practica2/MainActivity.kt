package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var editNombre = findViewById<EditText>(R.id.editNombre);
        //es muy larg, por lo que es mejor utilizarlo de esta manera (solo para Kotlin):

        btnEnviar.setOnClickListener {
            val intent = Intent(this, SecondAct::class.java)
            intent.putExtra("nombre", editNombre.text.toString())
            startActivity(intent)
        }

    }
}