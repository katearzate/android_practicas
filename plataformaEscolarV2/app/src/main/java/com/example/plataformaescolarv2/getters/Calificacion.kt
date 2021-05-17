package com.example.plataformaescolarv2.getters

import java.io.Serializable

data class Calificacion(val calificacion:String? = "",
                        val nomMateriaCalificacion:String? = "",
                        val creditos:Int? = 0) : Serializable
