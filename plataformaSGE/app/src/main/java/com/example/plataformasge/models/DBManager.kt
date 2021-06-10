package com.example.plataformasge.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable

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
                CREATE TABLE users(
                    id INTEGER PRIMARY KEY NOT NULL,
                    nombre TEXT NOT NULL,
                    apellidos TEXT NOT NULL,
                    noControl TEXT NOT NULL,
                    contrasena TEXT NOT NULL,
                    carrera TEXT NOT NULL,
                    semestre INT NOT NULL,
                )
            """
            it.execSQL(sql)

            sql = """INSERT INTO users(nombre, apellidos, noControl, contrasena, carrera, semestre) 
                    VALUES('Katherine', 'Arzate Serrano', '18121684', '123', 'Ingenieria en TICS', 6)"""

            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    @Throws
    fun findUsers(noControl : Editable? = null) : ArrayList<User> {
        val db = readableDatabase

        var sql = "SELECT * FROM users"
        noControl?.let {
            if(it.isNotEmpty()) {
                sql += " WHERE noControl LIKE '%$it%'"
            }
        }

        val users = ArrayList<User>()

        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val contact = User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6)
            )

            users.add(contact)
        }
        db.close()

        return users
    }
}