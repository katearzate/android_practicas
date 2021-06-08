package com.example.contacts.models

import android.os.Parcel
import android.os.Parcelable

data class Contact(
    val id: Int,
    val name: String,
    val celphone: String,
    val favorite: Int?,
    val photo: ByteArray?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createByteArray()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(celphone)
        parcel.writeValue(favorite)
        parcel.writeByteArray(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }

}