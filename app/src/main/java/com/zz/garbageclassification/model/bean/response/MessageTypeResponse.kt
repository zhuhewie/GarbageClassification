package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : 获取消息类型列表的返回体<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/3 <p>
 * <p> 更改时间 : 2019/4/3 <p>
 * <p> 版本号 : 1 <p>
 *
 */
data class MessageTypeReponse(
    @SerializedName("data")
    val messageType: Data
)

data class Data(
    @SerializedName("OTHERNOTICE")
    val otherMessage: MessageType,           ////其他消息
    @SerializedName("ZKTING")
    val zkting: MessageType                 ////开庭消息
)

//其他消息
//开庭消息
@Parcelize
data class MessageType(
    val title: String,
    val unReadCount: Int
):Parcelable

