package com.example.contacts

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.InputType
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.contacts.models.Contact
import com.example.contacts.models.DBManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        var contact: Contact? = intent.getParcelableExtra("contact")

        name.setText(contact?.name)
        telephone.setText(contact?.celphone)
        contact?.photo?.let {
            image.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
        }


        call.setOnClickListener {
            var stringTelephone : String = String.format("tel: ${contact?.celphone}")

            val call = Intent(Intent.ACTION_DIAL)
            call.setData(Uri.parse(stringTelephone))
            ContextCompat.startActivity(this, call, null)
        }

        message.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enviar mensaje a ${contact?.name}")
            val inputMessage = EditText(this)
            inputMessage.setHint("Ingresar mensaje")
            inputMessage.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(inputMessage)

            builder.setPositiveButton("Mandar") { dialog, which ->
                var textMessage = inputMessage.text.toString()

                val smsManager = SmsManager.getDefault() as SmsManager
                smsManager.sendTextMessage(
                    contact?.celphone,
                    null, textMessage,
                    null,
                    null
                )
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()

            //showPopup(contact!!)

        }

        delete.setOnClickListener {
           try {
               //Toast.makeText(this, "Registro eliminado", Toast.LENGTH_LONG).show()
               val builder = AlertDialog.Builder(this)
               builder.setTitle("Advertencia")
               builder.setMessage("¿Seguro que desea Eliminar a ${contact?.name}?")
               builder.setPositiveButton("Si") { dialog, which ->
                   dbManager.delete(contact?.id.toString())

                   val intent = Intent(this, MainActivity::class.java)
                   startActivity(intent)
                   finish()
               }
               builder.setNegativeButton("No", null)
               val dialog: AlertDialog = builder.create()
               dialog.show()
           }catch (e: Exception){
               Toast.makeText(this, "Eliminacion fallida", Toast.LENGTH_LONG).show()
               e.printStackTrace()
           }
        }

        modify.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java).apply {
                putExtra("contact", contact)
            }
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun showPopup(contact: Contact){
        val inflater: LayoutInflater = LayoutInflater.from(this) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_message,null)

        val popupWindow = PopupWindow(
            view,
            LinearLayout.LayoutParams.WRAP_CONTENT,     //window width
            LinearLayout.LayoutParams.WRAP_CONTENT      //window height
        )

        popupWindow.width = 1000

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            popupWindow.elevation = 20.0F
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            val slideOut = Slide()
            slideOut.slideEdge = Gravity.BOTTOM
            popupWindow.exitTransition = slideOut
        }

        val contactName = view.findViewById<TextView>(R.id.popupTelephone)
        contactName.setText("Mensaje para ${contact.name}")

        val message = view.findViewById<EditText>(R.id.popupMessage)
        val btnSend = view.findViewById<MaterialButton>(R.id.popupBtnSend)

        btnSend.setOnClickListener {
            if (message.text.isNotEmpty()){
                /*val smsManager : SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    contact.celphone,
                    null,
                    message.toString(),
                    null,
                    null
                )*/
                Toast.makeText(this, "Mensaje enviado!", Toast.LENGTH_LONG).show()
                println("MENSAJE COMPLETO")
                popupWindow.dismiss()
            }else{
                Toast.makeText(this, "Mensaje vacío", Toast.LENGTH_LONG).show()
                println("MENSAJE VACIOOOOO")
            }
        }

        val btnClosePopup = view.findViewById<FloatingActionButton>(R.id.popupBtnClose)
        btnClosePopup.setOnClickListener{
            popupWindow.dismiss()
        }

        TransitionManager.beginDelayedTransition(view as ViewGroup?)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}