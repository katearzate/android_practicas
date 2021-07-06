package com.example.projectubereats

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projectubereats.databinding.ActivityProductsBinding
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.utils.Tools.Companion.toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    private lateinit var commerce: Commerce

    private lateinit var url: String
    private val order = ArrayList<String>()
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commerce = intent.getSerializableExtra("commerce") as Commerce

        if (commerce == null){
            finish()
            "No tiene datos este negocio".toast(this)
        }

        url = resources.getString(R.string.api)

        commerce.photo?.let {
            val urlPhoto = "${url}assets/images/$it"
            Picasso.get().load(urlPhoto).into(binding.imgProducts)
        }

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = commerce.commerce

        val textInfoCommerce: TextView = findViewById(R.id.textInfoCommerce)
        textInfoCommerce.text = "Descripción: ${commerce.description}"
        textInfoCommerce.text = "${textInfoCommerce.text}\nDirección: ${commerce.address} (${commerce.lat},${commerce.lng})"
        textInfoCommerce.text = "${textInfoCommerce.text}\nCategoria: ${commerce.category}"

        
    }
}