package com.example.plataformasge

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.plataformasge.databinding.ActivitySigninBinding
import com.example.plataformasge.models.DBManager
import com.example.plataformasge.models.User
import java.util.ArrayList

class SigninActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private var _dbManager: DBManager? = null
    private val dbManager get() = _dbManager!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _dbManager = DBManager(this, "escolar", null, 1)

        supportActionBar?.hide()

        var noSemeste = ""

        binding.signInBtnReturn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //***************** semester number generator **********************
        val semestre = ArrayList<String>()
        for(i in 1..12) {
            semestre.add("$i")
        }
        binding.signInSemester.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, semestre)
        binding.signInSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p1?.let {
                    val textView = it.findViewById<TextView>(R.id.text1)
                    noSemeste = textView.text.toString()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //******************* Register a new user **************************
        binding.signInBtnConfirm.setOnClickListener {
            if (binding.signInName.text.toString().isNotEmpty() &&
                binding.signInLastnames.text.toString().isNotEmpty() &&
                binding.signInNoControl.text.toString().isNotEmpty() &&
                binding.signInCareer.text.toString().isNotEmpty() &&
                binding.signInPassword.text.toString().isNotEmpty()){

                dbManager.addUser(
                    User(
                        0,
                        binding.signInName.text.toString(),
                        binding.signInLastnames.text.toString(),
                        binding.signInNoControl.text.toString(),
                        binding.signInCareer.text.toString(),
                        noSemeste,
                        binding.signInPassword.text.toString()
                    )
                )
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                showAlert()
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("¡No se ha completado todos los campos!")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}