package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : 首页 - 待办 - 签名案件实体类<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/26 <p>
 * <p> 更改时间 : 2019/3/26 <p>
 * <p> 版本号 : 1 <p>
 *
 */


/**
 *  "recordsTotal":19,
"totalPage":2,
 */
data class SignCaseResponse<T>(
    @SerializedName("content")
    val caseList: List<T>,
    val recordsTotal: Int,
    val totalPage: Int
)


/**
 * {
"mainInfoCid":"LdKBoTu0XC9U",
"docType":"裁决书",
"displayNo":"（2018）穗仲案字第 5503 号",
"secretary":"办案秘书1",
"telephone":"020-83283925",
"requestDate":1540887081000,
"signDate":null,
"status":"已签名"
}
 */
@Parcelize
data class SignCase(
    var displayNo       : String?,
    var docType         : String?,
    var mainInfoCid     : String?,
    var requestDate     : String?,
    var secretary       : String?,
    var signDate        : String?,
    var status          : String?,
    var telephone       : String?
) : Parcelable

/**
 * 开庭案件信息实体
 *
 * {
"START_TIME":1538002800000,
"END_TIME":1538005500000,
"CATEGORY":"总会",
"ADDRESS":"广州市沿江中路298号江湾大酒店A座8楼",
"DISPLAY_NO":"（2018）穗仲网案字第15093号",
"CID":"D5wJ3Rl0WAGn",
"COURT_OPEN_DATE":1537977600000,
"NAME":"第一仲裁庭"
}
 */
data class SessionCase(
    val ADDRESS: String,
    val CATEGORY: String,
    val CID: String,
    val COURT_OPEN_DATE: String,
    val DISPLAY_NO: String,
    val END_TIME: String,
    val NAME: String,
    val START_TIME: String
)