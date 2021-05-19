package com.example.plataformaescolarv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformaescolarv2.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miJson = resources.getString(R.string.jsonUsuarios)
        val jsonUsuarios = JSONObject(miJson)
        val arrayUsuarios = jsonUsuarios.getJSONArray("usuarios")

        binding.btnLogin.setOnClickListener {
            /*val noControl = binding.noControlLogin.text.toString()
            val contrasena = binding.contrasenaLogin.text.toString()

            var encontrado = false
            for (i in 0..(arrayUsuarios.length() - 1)) {
                //println(arrayUsuarios[i].toString())
                val jsonUsuario = arrayUsuarios.getJSONObject(i)
                if (jsonUsuario.getString("noControl").trim().equals(noControl.trim())
                    && jsonUsuario.getString("contrasena").trim().equals(contrasena.trim())
                ) {
                    encontrado = true
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("usuario", jsonUsuario.toString())
                    startActivity(intent)
                    finish()
                }
            }
            if (!encontrado) {
                showAlert()
            }*/

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.redirigirSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("BD", miJson)
            startActivity(intent)
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Numero de control o contraseña incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}