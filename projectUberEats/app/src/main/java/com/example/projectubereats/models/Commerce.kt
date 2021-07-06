package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Commerce(
    @SerializedName("id")
    val id: Int,
    @SerializedName("negocio")
    val commerce: String,
    @SerializedName("descripcion")
    val description: String,
    @SerializedName("direccion")
    val address: String,
    @SerializedName("latitud")
    val lat: Double,
    @SerializedName("longitud")
    val lng: Double,
    @SerializedName("id_categoria")
    val idCategory: Int,
    @SerializedName("categoria")
    val category: String,
    @SerializedName("favorito")
    val favorite: Boolean,
    @SerializedName("foto")
    val photo: String?
) : Serializable
