package com.example.plataformasge

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.plataformasge.databinding.ActivitySigninBinding
import java.util.ArrayList

class SigninActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private var noSemeste = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.signInBtnReturn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //********* semester number generator **********
        val semestre = ArrayList<String>()
        for(i in 1..12) {
            semestre.add("$i")
        }
        binding.signInSemester.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, semestre)
        binding.signInSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p1?.let {
                    val textView = it.findViewById<TextView>(android.R.id.text1)
                    noSemeste = textView.text.toString()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //************* Register a new user *************
        binding.signInBtnConfirm.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}