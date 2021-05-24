package com.example.basicactivity.myobjects

import android.media.Image
import java.io.Serializable

data class Compra(val horario:String, val pelicula:String, val cantidad:Int, val total:Int, val img:Int) : Serializable