package com.example.projectubereats.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.jvm.Throws

class DBManager(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            val sql = """
               CREATE TABLE usuario(
                    id INTEGER PRIMARY KEY,
                    usuario TEXT,
                    nombre TEXT,
                    celular TEXT
               ) 
            """

            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    @Throws
    fun removeUsr() {
        val db = writableDatabase
        db.execSQL("DELETE FROM usuario")

        db.close()
    }

    @Throws
    fun setUsr(usuario: User) {
        val db = writableDatabase

        var sql = "DELETE FROM usuario"

        db.execSQL(sql)

        sql = "INSERT INTO usuario VALUES(${usuario.id},'${usuario.usr}','${usuario.name}','${usuario.celphone}')"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun getUsr() : User? {
        val db = readableDatabase

        val sql = "SELECT * FROM usuario"

        val cursor = db.rawQuery(sql, null)

        var usuario: User? = null
        if(cursor.moveToNext()) {
            usuario = User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
        }
        cursor.close()
        db.close()

        return usuario
    }
}