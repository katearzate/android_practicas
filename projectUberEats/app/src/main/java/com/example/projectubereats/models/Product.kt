package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product(
    @SerializedName("id_product")
    val id: Int,
    @SerializedName("product")
    val product: String,
    @SerializedName("product_description")
    val productDescription: String,
    @SerializedName("id_business")
    val idCommerce: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("photo")
    val photo: String?
) : Serializable