package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformaescolar.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miJson = resources.getString(R.string.jsonUsuarios)
        val jsonUsuarios = JSONObject(miJson)                
        val arrayUsuarios = jsonUsuarios.getJSONArray("usuarios")

        //var jsonElement : JsonObject = JsonParser.parseString(Usuario.json).asJsonObject

        binding.btnLogin.setOnClickListener {
            val noControl = binding.noControlLogin.text.toString()
            val contrasena = binding.contrasenaLogin.text.toString()

            var encontrado = false
            for (i in 0..(arrayUsuarios.length()-1)){
                print("mensaje\n")
                println(arrayUsuarios[i].toString())
                val jsonUsuario1 = arrayUsuarios.getJSONObject(i)
                if (jsonUsuario1.getString("noControl").trim().equals(noControl.trim())
                        && jsonUsuario1.getString("contrasena").trim().equals(contrasena.trim())){
                    encontrado = true
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("usuario", jsonUsuario1.toString())
                    startActivity(intent)
                    finish()
                }
            }
            if(!encontrado){
                showAlert()
            }

            /*if((binding.contrasenaLogin.text.toString() == jsonElement.get("contrasena").asString) &&
                (binding.noControlLogin.text.toString() == jsonElement.get("noControl").asString)){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                showAlert()
            }*/


        }

        binding.redirigirSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("BD", miJson)
            startActivity(intent)
        }
    }

    //Muestra alerta si el numero de control o la contraseña ingresada son erroneas
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Numero de control o contraseña incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}