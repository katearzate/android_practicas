package com.example.plataformaescolarv2.getters

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import java.io.Serializable

data class Materia(var nombreMateria: String? = "",
                   var profesor: String? = "",
                   var dia1: String? = "",
                   var dia2: String? = "",
                   var dia3: String? = "",
                   var dia4: String? = "",
                   var dia5: String? = "",
                   var grupo: String? = "",
                   var calificacion: String? = "",
                   var creditos: Int? = 0
                   //var horarios: JSONArray? = null,
                   //var aulas: JSONArray? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombreMateria)
        parcel.writeString(profesor)
        parcel.writeString(dia1)
        parcel.writeString(dia2)
        parcel.writeString(dia3)
        parcel.writeString(dia4)
        parcel.writeString(dia5)
        parcel.writeString(grupo)
        parcel.writeString(calificacion)
        parcel.writeValue(creditos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Materia> {
        override fun createFromParcel(parcel: Parcel): Materia {
            return Materia(parcel)
        }

        override fun newArray(size: Int): Array<Materia?> {
            return arrayOfNulls(size)
        }
    }
}