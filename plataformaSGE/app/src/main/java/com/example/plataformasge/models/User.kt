package com.example.plataformasge.models

import android.os.Parcel
import android.os.Parcelable

data class User(val id: Int,
                val name:String,
                val lastNames:String,
                val noControl:String,
                val career:String,
                val semester:String,
                val password:String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(lastNames)
        parcel.writeString(noControl)
        parcel.writeString(career)
        parcel.writeString(semester)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}