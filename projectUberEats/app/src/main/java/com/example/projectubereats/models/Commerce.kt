package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Commerce(
    @SerializedName("id_business")
    val id: Int,
    @SerializedName("business")
    val commerce: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("latitude")
    val lat: Double,
    @SerializedName("longitude")
    val lng: Double,
    @SerializedName("id_category")
    val idCategory: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("photo")
    val photo: String?
) : Serializable
