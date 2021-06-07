package com.example.contacts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.contacts.models.Contact
import com.example.contacts.models.DBManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.lang.Exception

class OptionsContactActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options_contact)

        dbManager = DBManager(
            this,
            resources.getString(R.string.db_name),
            null,
            resources.getInteger(R.integer.db_version)
        )

        lateinit var image : ImageView
        lateinit var name : TextView
        lateinit var telephone : TextView
        lateinit var call : ExtendedFloatingActionButton
        lateinit var message : ExtendedFloatingActionButton
        lateinit var delete : MaterialButton
        lateinit var modify : MaterialButton

        image = findViewById(R.id.optionsImage)
        name = findViewById(R.id.optionsName)
        telephone = findViewById(R.id.optionsTelephone)
        call = findViewById(R.id.optionsCall)
        message = findViewById(R.id.optionsMessage)
        delete = findViewById(R.id.optionsBtnDelete)
        modify = findViewById(R.id.optionsBtnEdit)

        val telephoneInt = intent.getStringExtra("telephone")
        name.setText(intent.getStringExtra("name"))
        telephone.setText(telephoneInt)

        call.setOnClickListener {
            var stringTelephone : String = String.format("tel: ${telephoneInt}")

            val call = Intent(Intent.ACTION_DIAL)
            call.setData(Uri.parse(stringTelephone))
            ContextCompat.startActivity(this, call, null)
        }

        message.setOnClickListener {
            var stringMessage : String = String.format("smsto: ${telephoneInt}")

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.parse(stringMessage))
            intent.putExtra("sms_body", "")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }else{
                println("NO SE HA LOGRA ACCEDER A MENSAJES")
            }
        }

        delete.setOnClickListener {
           try {
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_LONG).show()
                dbManager.delete(intent.getStringExtra("id"))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }catch (e: Exception){
                Toast.makeText(this, "Eliminacion fallida", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }

        modify.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}