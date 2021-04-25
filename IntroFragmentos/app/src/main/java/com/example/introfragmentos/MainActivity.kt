package com.example.introfragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by vie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editActivity = findViewById<EditText>(R.id.editMsgActivity)
        val btnActivity = findViewById<Button>(R.id.btnActivity)

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
        }*/


        /*
        *******    METODO 2 :    **********

        val fragmento = supportFragmentManager.findFragmentById(R.id.fragment) as BlankFragment
        btnActivity.setOnClickListener {
            val texto = "Activity dijo: ${editActivity.text}"
            editActivity.setText("")

            val editFragmento = fragmento.activity?.findViewById<EditText>(R.id.editMsgFragment)
            editFragmento?.setText(texto)
        **************************************************************************
        *
        }*/

        val fragmento = supportFragmentManager.findFragmentById(R.id.fragment) as BlankFragment


        btnActivity.setOnClickListener {
            val texto = "Activity dijo: ${editActivity.text}"
            viewModel.setText(texto)
        }

    }

}