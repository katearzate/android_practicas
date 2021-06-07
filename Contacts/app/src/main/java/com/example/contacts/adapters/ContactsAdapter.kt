package com.example.contacts.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.ContactActivity
import com.example.contacts.OptionsContactActivity
import com.example.contacts.R
import com.example.contacts.models.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactsAdapter (val context: Context, val res: Int, val contacts:ArrayList<Contact>)
    : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(context).inflate(res, null)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact) {
            val imagePhoto = itemView.findViewById<ImageView>(R.id.recyclerContactPhoto)
            val textName = itemView.findViewById<TextView>(R.id.recyclerContactName)
            val textTelephone = itemView.findViewById<TextView>(R.id.recyclerContactTelephone)
            val imageFavorite = itemView.findViewById<ImageView>(R.id.recyclerContactFavorite)
            val btnOptions = itemView.findViewById<FloatingActionButton>(R.id.recyclerContactOptions)

            textName.text = contact.name
            textTelephone.text = contact.celphone

            contact.favorite?.let {
                imageFavorite.setImageResource(
                    if(it == 1) android.R.drawable.star_big_on
                    else android.R.drawable.star_big_off
                )
            }

            contact.photo?.let {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                imagePhoto.setImageBitmap(
                    Bitmap.createScaledBitmap(bmp, bmp.width, bmp.height, false)
                )
            }

            btnOptions.setOnClickListener {

                var intent = Intent(context, OptionsContactActivity::class.java).apply {
                    putExtra("telephone", "${textTelephone.text}")

                }
                startActivity(context, intent, null)

            }
            /*
            btnOptions.setOnClickListener {
                fabEdit.visibility = if(fabEdit.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                fabDelete.visibility = if(fabDelete.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                fabCall.visibility = if(fabCall.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                fabMsg.visibility = if(fabMsg.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
            }*/
        }

    }
}