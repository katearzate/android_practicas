package com.example.plataformaescolarv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.plataformaescolarv2.databinding.ActivityDatosPersonalesBinding
import org.json.JSONObject

class DatosPersonalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatosPersonalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPersonalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var usuario = intent.getStringExtra("usuario")
        val jsonUsuario = JSONObject(usuario)
        val jsonUsuarioEdit = jsonUsuario
        println(jsonUsuario)

        binding.numeroControlDP.setText(jsonUsuario.getString("noControl"))
        binding.nombreDP.setText(jsonUsuario.getString("nombre"))
        binding.carreraDP.setText(jsonUsuario.getString("carrera"))
        binding.semestreDP.setText(jsonUsuario.getString("semestre"))
        binding.contrasenaDP.setText(jsonUsuario.getString("contrasena"))

        binding.btnActualizar.setOnClickListener {
            jsonUsuarioEdit.put("nombre", binding.nombreDP.text.toString())
            jsonUsuarioEdit.put("noControl", binding.numeroControlDP.text.toString())
            jsonUsuarioEdit.put("carrera", binding.carreraDP.text.toString())
            jsonUsuarioEdit.put("semestre", binding.semestreDP.text.toString())
            jsonUsuarioEdit.put("contrasena", binding.contrasenaDP.text.toString())
            println(jsonUsuarioEdit)
        }

        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("usuario", jsonUsuarioEdit.toString())
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}