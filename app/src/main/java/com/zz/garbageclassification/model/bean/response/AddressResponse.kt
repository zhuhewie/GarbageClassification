package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/21 <p>
 * <p> 更改时间 : 2019/3/21 <p>
 * <p> 版本号 : 1 <p>
 *
 *
 *
 *       {
 *           "_id":"guangzhouHeadquarters",
 *           "addressName":"广州总会",
 *           "gaodeMapInfo":{
 *           "longitude":"113.277192",
 *           "latitude":"23.115077"
 *       },
 *           "baiduMapInfo":{
 *           "longitude":"113.283668",
 *           "latitude":"23.121374"
 *       },
 *           "addressDetail":"广州市越秀区沿江中路298号"
 *       }
 */
@Parcelize
data class AddressResponse(
    val _id: String,
    val addressDetail: String,
    val addressName: String,
    val baiduMapInfo: BaiduMapInfo,
    val gaodeMapInfo: GaodeMapInfo
):Parcelable
@Parcelize
data class GaodeMapInfo(
    val latitude: Float,
    val longitude: Float
):Parcelable
@Parcelize
data class BaiduMapInfo(
    val latitude: Float,
    val longitude: Float
):Parcelable