package com.example.projectubereats

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.databinding.ActivityProductsBinding
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.models.Product
import com.example.projectubereats.utils.Tools
import com.example.projectubereats.utils.Tools.Companion.dbGet
import com.example.projectubereats.utils.Tools.Companion.toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import mx.edu.itm.link.dadm_u3proyb.adapters.ProductsAdapter
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    private lateinit var commerce: Commerce
    private lateinit var recyclerProducts: RecyclerView
    private lateinit var btnBuy: ExtendedFloatingActionButton

    private lateinit var url: String
    private val order = ArrayList<String>()
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        commerce = intent.getSerializableExtra("commerce") as Commerce
        recyclerProducts = findViewById(R.id.recyclerProducts)
        btnBuy = findViewById(R.id.btnBuyProducts)

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
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).setExpandedTitleTextColor(
            ColorStateList.valueOf(Color.WHITE))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).setCollapsedTitleTextColor(Color.WHITE)

        val textInfoCommerce: TextView = findViewById(R.id.textInfoCommerce)
        textInfoCommerce.text = "Descripción: ${commerce.description}"
        textInfoCommerce.text = "${textInfoCommerce.text}\nDirección: ${commerce.address} (${commerce.lat},${commerce.lng})"
        textInfoCommerce.text = "${textInfoCommerce.text}\nCategoria: ${commerce.category}"

        try {
            val endPoint = "${url}menu_negocio.php?id=${commerce.id}"
            object : Tools() {
                override fun formatResponse(response: String) {
                    try {
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")

                        val productos = ArrayList<Product>()
                        for(i in 0..output.length()-1) {
                            val jsonProduct = output.getJSONObject(i)

                            val producto = Gson().fromJson(jsonProduct.toString(), Product::class.java)

                            productos.add(producto)
                        }

                        recyclerProducts.adapter = object: ProductsAdapter(
                            this@ProductsActivity,
                            R.layout.recycler_row_products,
                            productos
                        ) {
                            override fun crearPedido(producto: Product) {
                                this@ProductsActivity.pedir(producto)
                            }
                        }
                        recyclerProducts.layoutManager = LinearLayoutManager(this@ProductsActivity)

                    } catch (e: Exception) {
                        e.printStackTrace()
                        "Error, no hay productos disponibles".toast(this@ProductsActivity)
                    }
                }
            }.consumeGet(this, endPoint)
        } catch (e: Exception) {
            "Error al cargar productos".toast(this)
            println("Error:\n$e")
            Log.e("Productos","Error\n$e")
        }

        //setSupportActionBar(findViewById(R.id.toolbar))

        binding.fabFavProducts.setOnClickListener { view ->
            try {
                this.dbGet()?.let{
                    val endP = "${url}fav.php"

                    val params = HashMap<String,String>()
                    params.put("usr", it.id.toString())
                    params.put("com", commerce.id.toString())

                    object : Tools(){
                        override fun formatResponse(response: String) {
                            //Log.d("Fav",response)
                            try {
                                val json = JSONObject(response)
                                val jsonOutput = json.getJSONArray("output")

                                if(jsonOutput.getString(0).equals("1")) {
                                    binding.fabFavProducts.setIconResource(R.mipmap.heart_24dp)
                                } else {
                                    binding.fabFavProducts.setIconResource(R.mipmap.heart_border_24dp)
                                }
                            } catch (e: Error) {
                                e.printStackTrace()
                                "Error en favoritos".toast(this@ProductsActivity)
                            }
                        }
                    }.consumePost(this,endP,params)

                    Snackbar.make(view, "Se actualizo favoritos", Snackbar.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error al actualizar favoritos".toast(this)
                Log.e("Fav","Error:\n$e")
            }
        }

        btnBuy.setOnClickListener {
            finish()
        }
    }

    private fun pedir(p: Product) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Tu pedido:")

        // Lista del pedido y su evento click
        alert.setAdapter(
            ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            order
        )
        ) { dialogInterface: DialogInterface, i: Int ->
            // Evento click de la lista
        }

        // Campo para agregar la cantidad del producto
        val editCantidad = EditText(this)
        editCantidad.setHint("Cantidad de ${p.product}:")
        editCantidad.inputType = InputType.TYPE_CLASS_NUMBER
        alert.setView(editCantidad)

        // Botones
        alert.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        alert.setPositiveButton("Agregar") { dialogInterface: DialogInterface, i: Int ->
            if(editCantidad.text.isNotEmpty()) {
                val subtotal = (p.price * editCantidad.text.toString().toDouble())
                total += subtotal
                order.add("${editCantidad.text} ... ${p.product}: $subtotal")
                btnBuy.text = "Realizar compra por \$${total}"

                MotionToast.createToast(
                    this,
                    "Se agregó",
                    "Producto agregado a tu pedido",
                    MotionToast.TOAST_SUCCESS,
                    MotionToast.GRAVITY_CENTER,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.helvetica_regular)
                )
            }
            dialogInterface.dismiss()
        }

        alert.show()
    }
}