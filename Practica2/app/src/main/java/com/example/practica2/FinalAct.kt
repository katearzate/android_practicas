package com.example.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_final.*

class FinalAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val nombre = intent.getStringExtra("nombre")
        val edad = intent.getIntExtra("edad", -1)

        val button = Button(this)
        button.text = "Enviar"
        button.setOnClickListener {
            val estatura = editEstatura.text.toString()
            val msg = "Tu nombre es: $nombre\n Tu edad es: $edad\nTu estatura es: $estatura"

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
        linear.addView(button)
    }
}