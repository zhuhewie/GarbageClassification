package com.zz.garbageclassification.model.bean.response

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/16 <p>
 * <p> 更改时间 : 2019/4/16 <p>
 * <p> 版本号 : 1 <p>
 *
 */
data class UpdateResponse(
    val needUpdate: Boolean,
    val isNotice: Boolean,
    val isForce: Boolean,
    val version: String,
    val updateTitle: String,
    val url: String,
    val updateMessage: String
)
