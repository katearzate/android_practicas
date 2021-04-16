package com.example.plataformaescolar.clases

data class Usuario(
    var nombre: String? = null,
    var noControl: String? = null,
    var carrera: String? = null,
    var semestre: String? = null,
    var contrasena: String? = null
){
    //instancia
    //val usuario1 = Usuario("Katherine","18121600", "TICS","6","123")
    companion object{
        var json : String =
            "{'nombre': 'Katherine Arzate'," +
                    "'noControl': '18121600'," +
                    "'carrera': 'TICS'," +
                    "'semestre': '6'," +
                    "'contrasena': '123'}"
    }
}
