package com.example.plataformaescolarv2.getters

import org.json.JSONArray

data class Materia(var nombreMateria: String? = "",
                   var profesor: String? = "",
                   var hora: String? = "",
                   var aula: String? = "",
                   var grupo: String? = "",
                   var calificacion: String? = "",
                   var creditos: Int? = 0,
                   var horarios: JSONArray? = null,
                   var aulas: JSONArray? = null){
}