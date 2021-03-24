package com.example.listas.clases

import java.io.Serializable

//solo sera para los getters y setters de forma simplificada
data class Producto(val nombre:String, val precio:Double, val cantidad:Int, val imagen:Int) : Serializable