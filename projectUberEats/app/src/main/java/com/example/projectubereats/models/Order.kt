package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName

class Order (
    @SerializedName("id_business")
    val id: Int,
    @SerializedName("business")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("latitude")
    val lat: Double,
    @SerializedName("longitude")
    val lng: Double,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("total")
    val total: Double,
        )