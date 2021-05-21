package com.example.bd_operaciones.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.jvm.Throws


class DataBaseManager (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        //create DB from 'name', starting with the tables
        //in SQLite only exists 4 data types: INT, TEXT, REAL (double/float), BLOB (bytes)

        val sql = "CREATE TABLE products(id INTEGER PRIMARY KEY NOT NULL, details TEXT NOT NULL)"

        db?.let {
            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    //Function to Insert
    @Throws
    fun add(product: Product){
        val db = writableDatabase
        val sql = "INSERT INTO products(details) VALUES('${product.details}')"

        db.execSQL(sql)
        db.close()
    }

    @Throws
    fun show() : ArrayList<Product>{
        val db = readableDatabase
        val sql = "SELECT * FROM products"
        val pointer = db.rawQuery(sql, null)

        val results = ArrayList<Product>()
        while (pointer.moveToNext()){
            val p = Product(pointer.getInt(0), pointer.getString(1))
            results.add(p)
        }
        db.close()
        return results
    }

    @Throws
    fun edit(old : Product, new : Product){
        val db = writableDatabase
        val sql = "UPDATE products SET details='${new.details}' WHERE id=${old.id}"

        db.execSQL(sql)
        db.close()
    }

    fun delete(product: Product) {
        val db = writableDatabase
        val sql = "DELETE FROM products WHERE id=${product.id}"

        db.execSQL(sql)
        db.close()
    }
}