package com.example.plataformasge.models

data class User(val id: Int,
                val nombre:String = "",
                val apellidos:String = "",
                val noControl:String = "",
                val contrasena:String = "",
                val carrera:String = "",
                val semestre:Int = 0)