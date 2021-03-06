package com.example.basicactivity.myobjects

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.basicactivity.myobjects.Compra
import kotlin.jvm.Throws


class DatabaseManager (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE compras(
                id INTEGER PRIMARY KEY NOT NULL, 
                pelicula TEXT NOT NULL, 
                hora TEXT NOT NULL, 
                cantidad INT NOT NULL, 
                total INT NOT NULL, 
                img INT NOT NULL
            );""".trimIndent()

        db?.let {
            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    @Throws
    fun add(compra: Compra){
        val db = writableDatabase
        val sql = """
            INSERT INTO compras(pelicula, hora, cantidad, total, img) VALUES(
                '${compra.pelicula}', 
                '${compra.horario}', 
                '${compra.cantidad}', 
                '${compra.total}', 
                '${compra.img}'
            );""".trimIndent()

        db.execSQL(sql)
        db.close()
    }

    @Throws
    fun show() : ArrayList<Compra>{
        val db = readableDatabase
        val pointer = db.rawQuery("SELECT * FROM compras", null)

        val results = ArrayList<Compra>()
        while (pointer.moveToNext()){
            results.add(
                Compra(
                    pointer.getInt(0),
                    pointer.getString(1),
                    pointer.getString(2),
                    pointer.getInt(3),
                    pointer.getInt(4),
                    pointer.getInt(5)
                )
            )
        }
        db.close()
        return results
    }

    fun delete(compra: Compra) {
        val db = writableDatabase
        val sql = "DELETE FROM compras WHERE id=${compra.id}"

        db.execSQL(sql)
        db.close()
    }
}