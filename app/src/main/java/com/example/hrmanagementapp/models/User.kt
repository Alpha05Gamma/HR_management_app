package com.example.hrmanagementapp.models

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

class User(
    val id: Int,
    val keycloakId: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
    val birthDate: LocalDate,
    val status: String,
    val email: String,
    val discord: String,
    val telegram: String,
    val loginable: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as LocalDate,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(keycloakId)
        parcel.writeString(surname)
        parcel.writeString(name)
        parcel.writeString(patronymic)
        parcel.writeSerializable(birthDate)
        parcel.writeString(status)
        parcel.writeString(email)
        parcel.writeString(discord)
        parcel.writeString(telegram)
        parcel.writeByte(if (loginable) 1 else 0)
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
