package com.example.plataformasge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformasge.databinding.ActivityHomeBinding
import com.example.plataformasge.databinding.ActivityRegisterScheduleBinding
import com.example.plataformasge.models.User

class RegisterScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var user = intent.getParcelableExtra<User>("user")
        //TODO: agregar al usuario para que se muestren los datos

        binding.registerBtnEnter.setOnClickListener {
            startActivity(Intent(this, SubjectsElectionActivity::class.java))
        }

        binding.registerBtnOmit.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        supportActionBar?.hide()

    }
}