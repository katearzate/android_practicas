package com.example.plataformasge.models

data class User(val id: Int,
                val name:String = "",
                val lastNames:String = "",
                val noControl:String = "",
                val password:String = "",
                val career:String = "",
                val semester:String = "")