package com.example.plataformasge

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformasge.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.redirigirSignin.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
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