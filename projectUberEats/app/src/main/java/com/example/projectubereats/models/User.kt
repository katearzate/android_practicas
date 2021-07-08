package com.example.projectubereats.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User (
    @SerializedName("id_user")
    val id: Int,
    @SerializedName("mail")
    val usr: String,
    @SerializedName("password")
    val pass: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("telephone")
    val celphone: String
): Serializable