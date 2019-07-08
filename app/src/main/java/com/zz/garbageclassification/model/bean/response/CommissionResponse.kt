package com.zz.garbageclassification.model.bean.response

import android.os.Parcel
import android.os.Parcelable


data class CommissionResponse(
    var applyStatus: String,        //在聘
    var cid: String,                //Eac99VJm8kht
    var commissionId: String,       //Eac99VJm8kht
    var commissionName: String,     //中国广州仲裁委员会
    var name: String                //中国广州仲裁委员会
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        applyStatus?.apply      { parcel.writeString(this) }
        cid?.apply              { parcel.writeString(this) }
        commissionId?.apply     { parcel.writeString(this) }
        commissionName?.apply   { parcel.writeString(this) }
        name?.apply             { parcel.writeString(this) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommissionResponse> {
        override fun createFromParcel(parcel: Parcel): CommissionResponse {
            return CommissionResponse(parcel)
        }

        override fun newArray(size: Int): Array<CommissionResponse?> {
            return arrayOfNulls(size)
        }
    }
}