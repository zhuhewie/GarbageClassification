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
data class CaseCountResponse(
    val NONWEBCASE: List<Int>,
    val WEBCASE: List<Int>
): Parcelable