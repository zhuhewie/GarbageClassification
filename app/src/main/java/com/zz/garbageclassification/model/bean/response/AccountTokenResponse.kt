package com.zz.garbageclassification.model.bean.response

import com.google.gson.annotations.SerializedName

data class AccountTokenResponse(
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("refresh_token")
    var refreshToken: String,

    @SerializedName("imUserParam")
    var imUserParam: ImgUserParam,
    @SerializedName("accountCid")
    var accountCid: String,
    @SerializedName("expires_in")
    var expiresIn: String

    ) {

}