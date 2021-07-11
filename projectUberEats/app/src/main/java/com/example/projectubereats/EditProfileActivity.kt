package com.example.projectubereats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectubereats.databinding.ActivityEditProfileBinding
import com.example.projectubereats.utils.Tools

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = "${resources.getString(R.string.api)}"    //agregar extension

        binding.editProfileEditMail.setText("")     //pasar los valores del fragmento

        binding.editProfileBtnUpdate.setOnClickListener {
            val params = HashMap<String,String>()

            params.put("usr", binding.editProfileEditMail.text.toString())
            params.put("pass", binding.editProfileEditPass.text.toString())
            params.put("name", binding.editProfileEditName.text.toString())
            params.put("tel", binding.editProfileEditTel.text.toString())

            object : Tools(){
                override fun formatResponse(response: String) {}
            }.consumePost(this, url, params)

            startActivity(Intent(this, MainActivity::class.java))
            //cerrar sesión!
        }
    }

}