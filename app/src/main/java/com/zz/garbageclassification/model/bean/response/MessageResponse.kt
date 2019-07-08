package com.zz.garbageclassification.model.bean.response

import com.google.gson.annotations.SerializedName

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/3 <p>
 * <p> 更改时间 : 2019/4/3 <p>
 * <p> 版本号 : 1 <p>
 *MessageResponse
 */

data class MessageResponse(
    @SerializedName(value = "ZKTING",alternate = ["OTHERNOTICE"])
    val messageAll: Message
)

data class Message(
    val content: List<MessageItem>,
    val title: String,
    val totalPage: Int,
    val unReadCount: Int
)

data class MessageItem(
    val androidPushMessageId: String,
    val androidPushStatus: String,
    val appCode: String,
    val cid: String,
    val confirmReceive: Boolean,
    val confirmReceiveTime: Long,
    val content: String,
    val createTime: Long,
    val iOSPushMessageId: String,
    val iOSPushStatus: String,
    var isCheck: Boolean,
    val messageParams: String,
    val messageType: String,
    val messageTypeId: String,
    val messageUid: String,
    val messageUrl: String,
    val portalId: String,
    val portalName: String,
    val pushFailMsg: String,
    val receiverId: String,
    val receiverName: String,
    val remark: String,
    val title: String,
    val userCheckTime: Long
)