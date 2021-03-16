package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rBtn:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnEnviar1.setOnClickListener {
            var rBtnId:Int = rGroup1.checkedRadioButtonId
            rBtn = findViewById(rBtnId)
            Toast.makeText(this, "${rBtn.text}", Toast.LENGTH_LONG).show()


            val intent = Intent(this, activity_second::class.java)
            intent.putExtra("primero", rBtn1.text.toString())
            startActivity(intent)

        }
    }
}