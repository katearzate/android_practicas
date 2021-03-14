package com.example.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val nombre = intent.getStringExtra("nombre")

        //operador opcional (previene de que truene el prog):
        nombre?.let {
            if(it.isEmpty()){
                println("esta vacio")
            }else {
                println("el valor de nombre es $it")
            }

            btnEnviar2.setOnClickListener {
                val intent = Intent(this,FinalAct::class.java)
                intent.putExtra("edad", editEdad.text.toString().toInt())
                finish()
            }
        }
    }
}