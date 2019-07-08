package com.zz.garbageclassification.model.bean.response

import com.contrarywind.interfaces.IPickerViewData

data class QuestionResponse(
    val cid: String,
    val commissionCid: String,
    val commissionName: String,
    val createAccountCid: String,
    val createTime: Long,
    val description: String,
    val examineQuestions: List<ExamineQuestion>,
    val title: String
)

open class ExamineQuestion(

) {
    var attachmentRequire: Boolean? = null      // 附件是否必填
    var cid: String? = null
    var defaultAnswer: String? = null              // 默认答案
    var maxAttaCount: Int? = null               // 最大附件数量
    var options: List<QuestionOptionInfo>? = null// 单选、多选 待选项
    var placeholder: String? = null                // 输入提示
    var question: String? = null
    var require: Boolean? = null                // 是否必填
    val type: String?=null                      // 枚举
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExamineQuestion

        if (cid.equals(other.cid) ) return true
        if (attachmentRequire != other.attachmentRequire) return false
        if (cid != other.cid) return false
        if (defaultAnswer != other.defaultAnswer) return false
        if (maxAttaCount != other.maxAttaCount) return false
        if (options != other.options) return false
        if (placeholder != other.placeholder) return false
        if (question != other.question) return false
        if (require != other.require) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attachmentRequire?.hashCode() ?: 0
        result = 31 * result + (cid?.hashCode() ?: 0)
        result = 31 * result + (defaultAnswer?.hashCode() ?: 0)
        result = 31 * result + (maxAttaCount ?: 0)
        result = 31 * result + (options?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + (question?.hashCode() ?: 0)
        result = 31 * result + (require?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }


}

class QuestionOptionInfo() : IPickerViewData {
    override fun getPickerViewText(): String {
        return option?.let { it }?:""
    }

    var cid: String? = null
    var option: String? = null
}

data class QuestionTips(
    var questionTips: String
) :ExamineQuestion()