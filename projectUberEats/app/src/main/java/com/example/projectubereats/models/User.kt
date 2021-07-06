package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User (
    @SerializedName("id")
    val id: Int,
    @SerializedName("usuario")
    val usr: String,
    @SerializedName("contrasenia")
    val pass: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("telefono")
    val celphone: String
): Serializable