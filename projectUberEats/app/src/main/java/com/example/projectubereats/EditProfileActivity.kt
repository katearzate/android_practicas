package com.example.projectubereats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.projectubereats.databinding.ActivityEditProfileBinding
import com.example.projectubereats.models.User
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.dbRemove
import com.example.projectubereats.utils.Tools.Companion.toast

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = resources.getString(R.string.api)+"actualizar_usuario.php"

        user = intent.getSerializableExtra("user") as User

        binding.editProfileMail.setText(user.usr)
        //binding.editProfilePass.setText(user.pass)
        binding.editProfileName.setText(user.name)
        binding.editProfileTel.setText(user.celphone)

        binding.editProfileBtnUpdate.setOnClickListener {
            if(binding.editProfilePass.text.isNotEmpty()){
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Actualización de datos")
                    .setMessage("¿Estás seguro que desea actualizar? (Se tendrá que iniciar sesión de nuevo)")
                    .setNegativeButton("Cancelar") { view, _ ->
                        view.dismiss()
                    }
                    .setPositiveButton("Aceptar") { view, _ ->
                        val params = HashMap<String,String?>()

                        params.put("id", user.id.toString())
                        params.put("usr", binding.editProfileMail.text.toString())
                        params.put("pass", binding.editProfilePass.text.toString())
                        params.put("name", binding.editProfileName.text.toString())
                        params.put("tel", binding.editProfileTel.text.toString())

                        object : Tools(){
                            override fun formatResponse(response: String) {}
                        }.consumePost(this, url, params)
                        this.dbRemove()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        "Datos actualizados".toast(this)

                        view.dismiss()
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()

            }else{
                "Campo(s) nulo(s)".toast(this)
            }

        }
    }

}