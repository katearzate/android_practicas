package com.example.projectubereats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectubereats.databinding.ActivitySignInBinding
import com.example.projectubereats.utils.Tools

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        url = "${resources.getString(R.string.api)}agrega_usuario.php"

        binding.signinBtnRegister.setOnClickListener {
            val params = HashMap<String,String?>()
            params.put("usr", binding.signinEditMail.text.toString())
            params.put("pass", binding.signinEditPass.text.toString())
            params.put("name", binding.signinEditName.text.toString())
            params.put("tel", binding.signinEditTel.text.toString())

            object : Tools(){
                override fun formatResponse(response: String) {}
            }.consumePost(this, url, params)
            finish()
        }
    }

}