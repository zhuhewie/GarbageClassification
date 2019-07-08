package com.zz.garbageclassification.model.bean.response

/**
 * <p> 文件描述 : 账号的基本信息<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/15 <p>
 * <p> 更改时间 : 2019/4/15 <p>
 * <p> 版本号 : 1 <p>
 *
 */
data class BasicInfoResponse(
    val content: Content,
    val status: String
)

data class Content(
    val appHeadIcon: String,
    val ecertificateStatus: String,
    val email: String,
    val hasSign: Boolean,
    val job: String,
    val name: String,
    val officialqrcode: String,
    val phone: String,
    val picSrc: String,
    val signedPhoto: SignedPhoto,
    val signedPhotoStatus: String
)


