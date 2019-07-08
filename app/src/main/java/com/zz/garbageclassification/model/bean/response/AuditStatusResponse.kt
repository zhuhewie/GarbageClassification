package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/17 <p>
 * <p> 更改时间 : 2019/4/17 <p>
 * <p> 版本号 : 1 <p>
 *
 */

data class AuditStatusResponse(
    val auditResponse: AuditResponse,
    val signedPhoto: SignedPhoto,
    val signedPhotoStatus: String,
    val submitSignPath: String
)
@Parcelize
data class SignedPhoto(
    val fileName: String,
    val signId: String,
    val storageCode: String,
    val uploadTime: Long,
    val uploader: String
) : Parcelable
