package com.example.basicactivity.myobjects

import java.io.Serializable

data class Movie(val id:Int, val name:String, val img:Int, val horarios:List<String>) : Serializable