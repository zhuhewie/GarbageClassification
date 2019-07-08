package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/8 <p>
 * <p> 更改时间 : 2019/4/8 <p>
 * <p> 版本号 : 1 <p>
 *
 */
@Parcelize
data class CaseStatsResponse(
    val closed: List<Closed>,
    val courtAndSession: CourtAndSession,
    val nonWebCase: List<NonWebCase>,
    val total: Total,
    val type: List<Type>,
    val webCase: List<WebCase>
) : Parcelable
@Parcelize
data class Type(
    val key: String,
    val number: Int
):Parcelable

@Parcelize
data class NonWebCase(
    val key: String,
    val number: Int
):Parcelable

@Parcelize
data class WebCase(
    val key: String,
    val number: Int
):Parcelable

@Parcelize
data class Total(
    val closed: Int,
    val doing: Int
) : Parcelable
@Parcelize
data class Closed(
    val key: String,
    val number: Int
):Parcelable
@Parcelize
data class CourtAndSession(
    val court: Int,
    val session: Int
):Parcelable