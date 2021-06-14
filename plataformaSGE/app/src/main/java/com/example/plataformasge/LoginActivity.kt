package com.example.plataformasge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformasge.databinding.ActivityLoginBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.User
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        _dbManager = DBManager(this, "escolar", null, 1)
        //dbManager.deleteDatabase(this, "escolar")

        binding.loginBtnEnter.setOnClickListener {
            if (binding.loginNoControl.text.toString().isNotEmpty() &&
                binding.loginPassword.text.toString().isNotEmpty()) {
                val user = dbManager.findUser(binding.loginNoControl.text.toString(), binding.loginPassword.text.toString())
                user?.let{
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("user", user)
                    }
                    println("USUARIO: ${user}")
                    startActivity(intent)
                }

            } else{
                showAlert()
            }
        }

        binding.redirectSignin.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
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