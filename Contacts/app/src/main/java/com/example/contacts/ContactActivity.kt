package com.example.contacts

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.adapters.ContactsAdapter
import com.example.contacts.models.Contact
import com.example.contacts.models.DBManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.ByteArrayOutputStream

class ContactActivity : AppCompatActivity() {

    lateinit var btnAdd: FloatingActionButton
    lateinit var recyclerContacts: RecyclerView
    lateinit var editSearch: EditText
    lateinit var imgContact: ImageView
    lateinit var btnEditPhoto: FloatingActionButton
    lateinit var editName: EditText
    lateinit var editTelephone: EditText
    lateinit var tgBtnFavorite: ToggleButton
    lateinit var btnCancel: ExtendedFloatingActionButton
    lateinit var btnSave: ExtendedFloatingActionButton

    lateinit var dbManager: DBManager
    var byteFoto : ByteArray? = null
    var edited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)



        imgContact = findViewById(R.id.contactImage)
        btnEditPhoto = findViewById(R.id.contactBtnEditPhoto)
        editName = findViewById(R.id.contactEditName)
        editTelephone = findViewById(R.id.contactEditTelephone)
        tgBtnFavorite = findViewById(R.id.contactTGBtnFavorite)
        btnCancel = findViewById(R.id.contactBtnCancel)
        btnSave = findViewById(R.id.contactBtnSave)

        dbManager = DBManager(
            this,
            resources.getString(R.string.db_name),
            null,
            resources.getInteger(R.integer.db_version)
        )

        var contact: Contact? = null
        if (intent.extras != null) {
            contact = intent.getParcelableExtra("contact")
            edited = true
        }

        if (edited == true) {
            editName.setText(contact?.name)
            editTelephone.setText(contact?.celphone)
            contact?.photo?.let {
                imgContact.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
            }
            tgBtnFavorite.isChecked = contact?.favorite == 1
        }

        btnEditPhoto.setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Select Image")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

            startActivityForResult(chooserIntent, 1)
        }

        tgBtnFavorite.setOnClickListener {
            if (tgBtnFavorite.isChecked) {
                tgBtnFavorite.textOn = "Es favorito"
                tgBtnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(android.R.drawable.star_big_on), null, null, null
                )
            } else {
                tgBtnFavorite.textOff = "No es favorito"
                tgBtnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(android.R.drawable.star_off), null, null, null
                )
            }
        }

        btnCancel.setOnClickListener { finish() }

        btnSave.setOnClickListener {
            //update database
            if (edited == true) {
                try {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Advertencia")
                    builder.setMessage("¿Seguro que desea editar este contacto?")
                    builder.setPositiveButton("Si") { dialog, which ->
                        dbManager.update(
                            Contact(
                                contact!!.id,
                                editName.text.toString(),
                                editTelephone.text.toString(),
                                tgBtnFavorite.isChecked.toInt(),
                                contact.photo,
                            )
                        )
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    builder.setNegativeButton("No") { dialog, which ->
                        finish()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()

                    //Toast.makeText(this, "Contacto actualizado", Toast.LENGTH_LONG).show()
                    println("Contacto ACTUALIZADO")

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            } else {
                var validated = true
                if (editName.text.isEmpty()) {
                    validated = false
                    editName.setError("El nombre es requerido")
                }
                if (editTelephone.text.isEmpty()) {
                    validated = false
                    editTelephone.setError("El celular es requerido")
                }

                //If everything goes OK, then:
                if (validated) {
                    val contacto = Contact(
                        0,
                        editName.text.toString(),
                        editTelephone.text.toString(),
                        if (tgBtnFavorite.isSelected) 1 else 0,
                        byteFoto
                    )
                    // Added to database
                    try {
                        dbManager.create(contacto)
                        Toast.makeText(
                            this,
                            "Se agrego el contacto ${editName.text}",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this, "Error al crear el contacto", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            data?.let {
                val uri = it.data
                val baos = ByteArrayOutputStream()
                try {
                    it.data?.let {
                        val fis = contentResolver.openInputStream(it)

                        fis?.let {
                            val buf = ByteArray(1024)
                            do {
                                val n = it.read(buf)
                                if(n != -1) {
                                    baos.write(buf, 0, n)
                                } else break
                            } while (true)

                            byteFoto = baos.toByteArray()
                        }
                    }

                    // Actualizar imageView
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    imgContact.setImageBitmap(bitmap)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun Boolean.toInt() = if (this) 1 else 0

}