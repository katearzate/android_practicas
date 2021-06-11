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
        var users = """
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
        var subjects = """
            CREATE TABLE subjects(
                id_subject INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL,
                code TEXT NOT NULL,
                semester TEXT NOT NULL,
                score TEXT,
                credits INT NOT NULL
            );
        """.trimIndent()
        var groups = """
            CREATE TABLE groups(
                id_group INTEGER PRIMARY KEY NOT NULL, 
                name TEXT NOT NULL, 
                horaMonday TEXT NOT NULL, 
                horaTuesday TEXT NOT NULL, 
                horaWednesday TEXT NOT NULL, 
                horaThursday TEXT NOT NULL, 
                horaFriday TEXT NOT NULL, 
                id_subject INTEGER NOT NULL,
                FOREIGN KEY (id_subject) REFERENCES subjects(id_subject) ON UPDATE CASCADE ON DELETE CASCADE
            );
        """.trimIndent()

        var user1 = """
            INSERT INTO users(name, lastNames, noControl, password, career, semester) 
                VALUES('Katherine', 'Arzate Serrano', '18121684', '123', 'Ingenieria en TICS', '6')
        """.trimIndent()

        db?.let {
            it.execSQL(users)
            it.execSQL(subjects)
            it.execSQL(groups)
            it.execSQL(user1)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    //************************************** USERS ***********************************************
    @Throws
    fun findUser(noControl : String, password :String) : ArrayList<User> {
        val db = readableDatabase

        var sql = "SELECT * FROM users WHERE "
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
                cursor.getString(6)
            )

            users.add(contact)
        }
        db.close()

        return users
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
}