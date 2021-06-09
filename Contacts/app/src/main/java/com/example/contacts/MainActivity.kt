package com.example.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.adapters.ContactsAdapter
import com.example.contacts.models.DBManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var btnAdd : FloatingActionButton
    lateinit var recyclerContacts : RecyclerView
    lateinit var editSearch : EditText

    lateinit var dbManager : DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAddContact)
        recyclerContacts = findViewById(R.id.recyclerContacts)
        editSearch = findViewById(R.id.editSearch)

        dbManager = DBManager(
            this,
            resources.getString(R.string.db_name),
            null,
            resources.getInteger(R.integer.db_version)
        )

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {   }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {
                    refreshContacts()
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        btnAdd.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
        refreshContacts()
    }

    private fun refreshContacts() {
        try {
            val contacts = dbManager.find(editSearch.text)

            recyclerContacts.adapter = ContactsAdapter(this,R.layout.recycler_contacts, contacts)
            recyclerContacts.layoutManager = LinearLayoutManager(this)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshContacts()
    }
}