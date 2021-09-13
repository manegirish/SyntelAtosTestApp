package com.test.syntelatostestapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/13/21
 * Last modified on 9/13/21
 */
class Room() : Parcelable {

    val id: Long? = null

    @SerializedName("created_at")
    val createdAt: String? = null

    val name: String? = null

    @SerializedName("max_occupancy")
    val maxOccupancy: Int = 0

    @SerializedName("is_occupied")
    val occupied: Boolean = false

    constructor(parcel: Parcel) : this()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {}

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}