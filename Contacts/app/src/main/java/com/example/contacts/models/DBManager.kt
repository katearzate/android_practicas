package com.example.contacts.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import androidx.core.database.getBlobOrNull
import androidx.core.database.getIntOrNull
import kotlin.jvm.Throws

class DBManager (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = ""

        db?.let {
            sql = """
                CREATE TABLE contacts(
                    id INTEGER PRIMARY KEY NOT NULL,
                    name TEXT NOT NULL,
                    celphone TEXT NOT NULL,
                    favorite INT,
                    photo BLOB
                )
            """
            it.execSQL(sql)

            sql = "INSERT INTO contacts(name,celphone) VALUES('Yo','4433000000')"

            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    @Throws
    fun find(name : Editable? = null) : ArrayList<Contact> {
        val db = readableDatabase

        var sql = "SELECT * FROM contacts"
        name?.let {
            if(it.isNotEmpty()) {
                sql += " WHERE name LIKE '%$it%'"
            }
        }

        val contacts = ArrayList<Contact>()

        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val contact = Contact(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getIntOrNull(3),
                cursor.getBlobOrNull(4)
            )

            contacts.add(contact)
        }
        db.close()

        return contacts
    }

    @Throws
    fun create(contact: Contact) {
        val db = writableDatabase

        var sql = ""
        if(contact.photo != null) {
            sql = "INSERT INTO contacts(name,celphone,favorite,photo) VALUES(?,?,?,?)"
        } else {
            sql = "INSERT INTO contacts(name,celphone,favorite) VALUES(?,?,?)"
        }
        val compileStm = db.compileStatement(sql)
        compileStm.bindString(1, contact.name)
        compileStm.bindString(2, contact.celphone)

        contact.favorite?.let{
            compileStm.bindLong(3, it.toLong())
        } ?: compileStm.bindNull(3)

        contact.photo?.let { compileStm.bindBlob(4, it) }

        compileStm.execute()

        db.close()
    }

    @Throws
    fun update(contact: Contact){
        val db = writableDatabase
        val values = ContentValues()

        values.put("name", contact.name)
        values.put("celphone", contact.celphone)
        values.put("favorite", contact.favorite)
        values.put("photo", contact.photo)

        db.update("contacts", values, "id = ?", arrayOf(contact.id.toString()))
        db.close()
    }

    @Throws
    fun delete(id : String? = null){
        val db = writableDatabase
        db.delete("contacts", "id=?", arrayOf(id))

        db.close()
    }
}