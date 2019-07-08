package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AnswerResponse(
    val answerList: List<Answer>,
    val cid: String,
    val createTime: Long
)
@Parcelize
data class Answer(
    var attachmentPaths : String? ="",       // 附件路径，多个用都好隔开
    var mainAnswer      : String? ="",       // 文本内容
    var questionCid     : String? ="",       // 对应的问题id
    var type            : String? =PostExamineQuestionType.TEXT.string       // 枚举 PostExamineQuestionType
) : Parcelable
