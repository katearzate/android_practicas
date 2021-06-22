package com.example.plataformasge.models

import android.os.Parcel
import android.os.Parcelable

data class Subject (var subjectName: String?,
                    var group: String? = "",
                    var profesor: String? = "",
                    var credits: Int = 0,
                    var score: String? = "",
                    var code: String?,
                    var hourMonday: String? = "",
                    var hourTuesday: String? = "",
                    var hourWednesday: String? = "",
                    var hourThursday: String? = "",
                    var hourFriday: String? = ""
): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(subjectName)
        parcel?.writeString(group)
        parcel?.writeString(profesor)
        parcel?.writeInt(credits)
        parcel?.writeString(score)
        parcel?.writeString(code)
        parcel?.writeString(hourMonday)
        parcel?.writeString(hourTuesday)
        parcel?.writeString(hourWednesday)
        parcel?.writeString(hourThursday)
        parcel?.writeString(hourFriday)
    }

    companion object CREATOR : Parcelable.Creator<Subject> {
        override fun createFromParcel(parcel: Parcel): Subject {
            return Subject(parcel)
        }

        override fun newArray(size: Int): Array<Subject?> {
            return arrayOfNulls(size)
        }
    }
}