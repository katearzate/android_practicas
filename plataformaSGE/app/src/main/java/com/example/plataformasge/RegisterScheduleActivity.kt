package com.example.plataformasge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformasge.databinding.ActivityHomeBinding
import com.example.plataformasge.databinding.ActivityRegisterScheduleBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.User

class RegisterScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScheduleBinding
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _dbManager = DBManager(this, "escolar", null, 1)

        var user = intent.getParcelableExtra<User>("user")

        binding.registerBtnEnter.setOnClickListener {
            dbManager.deleteSubjects()
            val intent = Intent(this, SubjectsElectionActivity::class.java).apply {
                putExtra("user", user)
            }
            startActivity(intent)
        }

        binding.registerBtnOmit.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("user", user)
            }
            startActivity(intent)
            finish()
        }

        supportActionBar?.hide()

    }
}