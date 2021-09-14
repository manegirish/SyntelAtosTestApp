package com.test.syntelatostestapp.models

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class People() : Parcelable {

    val avatar: String? = null
    val phone: String? = null
    val firstName: String? = null
    val lastName: String? = null
    val id: Long? = null
    val longitude: Double? = null
    val latitude: Double? = null
    val favouriteColor: String? = null
    val email: String? = null
    val jobTitle: String? = null
    val createdAt: String? = null

    fun name(): String {
        return StringBuilder().append(firstName ?: "").append(" ").append(lastName ?: "").toString()
    }

    constructor(parcel: Parcel) : this()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {}

    companion object CREATOR : Parcelable.Creator<People> {
        override fun createFromParcel(parcel: Parcel): People {
            return People(parcel)
        }

        override fun newArray(size: Int): Array<People?> {
            return arrayOfNulls(size)
        }
    }
}