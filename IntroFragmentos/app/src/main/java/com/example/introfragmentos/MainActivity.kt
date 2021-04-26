package com.example.introfragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.*

class MainActivity : AppCompatActivity() {

    private val viewModel: FragActViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmento = supportFragmentManager.findFragmentById(R.id.fragment) as BlankFragment
        val editActivity = findViewById<EditText>(R.id.editMsgActivity)
        val btnActivity = findViewById<Button>(R.id.btnActivity)

        btnActivity.setOnClickListener {
            editActivity.text.let {
                viewModel.setMessage("Fragmento 2 dijo: ${it}")
            }
            editActivity.setText("")
        }

        /*
        *******    METODO 1 : Forzar a reiniciar el ciclo de creacion   **********

        btnActivity.setOnClickListener {
            val texto = editActivity.text.toString()
            editActivity.setText("")

            val fragmento = BlankFragment.newInstance(texto, "param2")

            val transaccion = supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.fragment, fragmento)
            transaccion.commit()    //terminar transaccion
        **************************************************************************
        *
        }

        *******    METODO 2 :    **********

        val fragmento = supportFragmentManager.findFragmentById(R.id.fragment) as BlankFragment
        btnActivity.setOnClickListener {
            val texto = "Activity dijo: ${editActivity.text}"
            editActivity.setText("")

            val editFragmento = fragmento.activity?.findViewById<EditText>(R.id.editMsgFragment)
            editFragmento?.setText(texto)
        **************************************************************************

        }*/

    }

}