package com.example.contacts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class OptionsContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options_contact)

        val telephoneIntent = intent.getStringExtra("telephone")

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

        telephone.setText(telephoneIntent)

        call.setOnClickListener {
            var stringTelephone : String = String.format("tel: ${telephoneIntent}")

            val call = Intent(Intent.ACTION_DIAL)
            call.setData(Uri.parse(stringTelephone))
            ContextCompat.startActivity(this, call, null)
        }

    }
}