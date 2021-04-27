package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ActivityFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviarActivity.setOnClickListener {
            binding.editMensajeActivity.text.let {
                viewModel.setMensaje("Activity dijo: ${it}")
            }
            binding.editMensajeActivity.setText("")
        }

        /*
        *******    METODO 1 : Forzar a reiniciar el ciclo de creacion   **********

        binding.btnEnviarActivity.setOnClickListener {
            val texto = binding.editMensajeActivity.text.toString()
            binding.editMensajeActivity.setText("")

            val fragmento = BlankFragment.newInstance(texto, "param2")

            val transaccion = supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.fragment1, fragmento)
            transaccion.commit()    //terminar transaccion
        **************************************************************************
        *
        }


        **********    METODO 2 : Utilizar supportFragmentManager   **********

        val fragmento = supportFragmentManager.findFragmentById(R.id.fragment) as BlankFragment
        binding.btnEnviarActivity.setOnClickListener {
            val texto = "Activity dijo: ${binding.editMensajeActivity.text}"
            binding.editMensajeActivity.setText("")

            val editFragmento = fragmento.activity?.findViewById<EditText>(R.id.editMensajeActivity)
            editFragmento?.setText(texto)
        **************************************************************************
        *
        }*/

    }
}