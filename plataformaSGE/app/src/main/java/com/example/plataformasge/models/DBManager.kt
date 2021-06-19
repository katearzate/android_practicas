package com.example.plataformasge.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
                hourMonday TEXT, 
                hourTuesday TEXT, 
                hourWednesday TEXT, 
                hourThursday TEXT, 
                hourFriday TEXT, 
                id_subject INTEGER NOT NULL,
                FOREIGN KEY (id_subject) REFERENCES subjects(id_subject) ON UPDATE CASCADE ON DELETE CASCADE
            );
        """.trimIndent()

        val user1 = """
            INSERT INTO users(id_user, name, lastNames, noControl, password, career, semester) 
                VALUES(1, 'Katherine', 'Arzate Serrano', '18121684', '123', 'Ingenieria en TICS', '6');
        """.trimIndent()

        db?.let {
            it.execSQL(users)
            it.execSQL(subjects)
            it.execSQL(groups)
            it.execSQL(user1)

            //insert subjects and groups
            val lines = context?.getString(R.string.inserts)?.lines()
            lines?.forEach { line ->
                it.execSQL(line)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun deleteDatabase(context: Context, nombreDB: String){
        context.deleteDatabase(nombreDB)
    }


    //************************************** USERS ***********************************************
    fun findUser(noControl: String?, password: String?) : User? {
        val db = readableDatabase
        var user: User? = null

        if(noControl?.isNotEmpty()!! && password?.isNotEmpty()!!){
            val sql = "SELECT * FROM users WHERE noControl = '$noControl' AND password = '$password'"
            val cursor = db.rawQuery(sql, null)

            if(cursor.moveToNext())
            user = User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
            )
            cursor.close()
        }

        db.close()
        return user
    }

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

    fun updateUser(user: User){
        val db = writableDatabase
        val values = ContentValues()

        values.put("name", user.name)
        values.put("lastNames", user.lastNames)
        values.put("noControl", user.noControl)
        values.put("password", user.password)
        values.put("career", user.career)
        values.put("semester", user.semester)

        db.update("users", values, "id_user = ?", arrayOf(user.id.toString()))
        db.close()
    }


    //**************************************** SUBJECTS ********************************************
    fun showScores(semester: String): List<Score>{
        val db = readableDatabase

        val sql = "SELECT name, score, credits, semester FROM subjects WHERE semester = '$semester'"

        val subjects: MutableList<Score> = mutableListOf()
        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()){
            subjects.add(
                Score(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
                )
            )
        }
        cursor.close()
        db.close()

        return subjects
    }

    fun showGroups(subject: String): List<Subject>{
        val db = readableDatabase

        val sql = """SELECT s.name, g.name,  profesor, credits, score,
            hourMonday, hourTuesday, hourWednesday, hourThursday, hourFriday FROM groups AS g 
            INNER JOIN subjects AS s ON s.id_subject = g.id_subject
            WHERE s.semester = '$subject'; """.trimMargin()

        val groups: MutableList<Subject> = mutableListOf()
        val cursor = db.rawQuery(sql, null)

        while (cursor.moveToNext()){
            groups.add(
                Subject(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)
                )
            )
        }
        cursor.close()
        db.close()

        return groups
    }

}