package com.example.plataformasge.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.util.Log
import com.example.plataformasge.R

class DBManager (
    val context: Context?,
    val name: String?,
    val factory: SQLiteDatabase.CursorFactory?,
    val version: Int
) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        val users = """
            CREATE TABLE users(
                id_user INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL,
                lastNames TEXT NOT NULL,
                noControl TEXT NOT NULL,
                password TEXT NOT NULL,
                career TEXT NOT NULL,
                semester TEXT NOT NULL
            );
        """.trimIndent()
        val subjects = """
            CREATE TABLE subjects(
                id_subject INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL,
                code TEXT NOT NULL,
                semester TEXT NOT NULL,
                score TEXT,
                credits INT NOT NULL
            );
        """.trimIndent()
        val groups = """
            CREATE TABLE groups(
                id_group INTEGER PRIMARY KEY NOT NULL, 
                name TEXT NOT NULL,
                profesor TEXT NOT NULL,   
                hourMonday TEXT NOT NULL, 
                hourTuesday TEXT NOT NULL, 
                hourWednesday TEXT NOT NULL, 
                hourThursday TEXT NOT NULL, 
                hourFriday TEXT NOT NULL, 
                id_subject INTEGER NOT NULL,
                FOREIGN KEY (id_subject) REFERENCES subjects(id_subject) ON UPDATE CASCADE ON DELETE CASCADE
            );
        """.trimIndent()

        val user1 = """
            INSERT INTO users(name, lastNames, noControl, password, career, semester) 
                VALUES('Katherine', 'Arzate Serrano', '18121684', '123', 'Ingenieria en TICS', '6');
        """.trimIndent()

        db?.let {
            it.execSQL(users)
            it.execSQL(subjects)
            it.execSQL(groups)
            it.execSQL(user1)

            //insert subjects
            val lineas = context?.getString(R.string.inserts)?.lines()
            lineas?.forEach { linea ->
                it.execSQL(linea)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    //************************************** USERS ***********************************************
    @Throws
    fun findUser(noControl: String?, password: String?) : User? {
        val db = readableDatabase
        var contact: User? = null

        if(noControl?.isNotEmpty()!! && password?.isNotEmpty()!!){
            var sql = "SELECT * FROM users WHERE noControl LIKE '%$noControl%' AND password LIKE '$password'"
            val cursor = db.rawQuery(sql, null)

            if(cursor.moveToNext() != null)
            contact = User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
            )
        }
        db.close()
        return contact
    }

    @Throws
    fun addUser(user: User){
        val db = writableDatabase
        val sql = """
           INSERT INTO users(name, lastNames, noControl, password, career, semester) VALUES(
                '${user.name}',
                '${user.lastNames}',
                '${user.noControl}',
                '${user.password}',
                '${user.career}',
                '${user.semester}'
           ) 
        """.trimIndent()

        db.execSQL(sql)
        db.close()
    }

    @Throws
    fun deleteDatabase(context: Context, nombreDB: String){
        context.deleteDatabase(nombreDB)
    }
}