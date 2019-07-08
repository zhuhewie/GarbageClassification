package com.zz.garbageclassification.model.bean.response

import com.google.gson.annotations.SerializedName

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 *
 */
data class NewsResponse(
    @SerializedName("content")
    val newsList: List<News>,
//    val status: String,
    val totalCount: Int,
    val unReadCount: Int
) : ErrorResponse()

/**
 * 新闻
 */
data class News(
    val cid: String,
    val createTime: String,
    val isRead: String,
    val newType: String,
    val newsUrl: String,
    val title: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as News

        if (cid != other.cid) return false
        if (createTime != other.createTime) return false
        if (isRead != other.isRead) return false
        if (newType != other.newType) return false
        if (newsUrl != other.newsUrl) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cid.hashCode()
        result = 31 * result + createTime.hashCode()
        result = 31 * result + isRead.hashCode()
        result = 31 * result + newType.hashCode()
        result = 31 * result + newsUrl.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }

    override fun toString(): String {
        return "News(cid='$cid', createTime='$createTime', isRead='$isRead', newType='$newType', newsUrl='$newsUrl', title='$title')"
    }
}

/**
 * 新闻类型 普通新闻和微信新闻
 */
data class NewsTitle(
    var title : String
)