package com.zz.garbageclassification.model.bean.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/15 <p>
 * <p> 更改时间 : 2019/4/15 <p>
 * <p> 版本号 : 1 <p>
 *
 */

data class PersonMaterResponse(
    val _id: String,
    val addComponents: Int,
    val addDefaultPortal: Int,
    val applyComments: String,
    val applyDateTime: Long,
    val applySignName: String,
    val applyType: String,
    val arbitratorCid: String,
    val arbitratorId: Int,
    val bankInfo: BankInfo,
    val commissionId: String,
    val commissionName: String,
    val ecertificateApply: EcertificateApply,
    val hasCertificate: Boolean,
    val icCardNo: String,
    val logoPath: String,
    val passTime: Long,
    val postExamineAnswer: PostExamineAnswer,
    val promise: String,
    val promiseSignName: String,
    val promiseTime: Long,
    val respAccountName: String,
    val respComments: String,
    val respHistoryList: List<String>,
    val respTime: Long,
    val sourceArbitratorId: String,
    val staLogoPath: String,
    val status: String,
    val submitRequest: List<SubmitRequest>,
    val switchArbitrateCapable: String
)
@Parcelize
data class EcertificateApply(
    val _id: String,
    val applyDate: Long,            // 申请时间、提交年审的时间
    val awardDate: Long,            // 授予时间，第一次审核通过默认为在聘时间，如果有年审，这时间需要确认
    val operateDate: Long,          // 审核为有效生成证书、后台设置为过期、废止等操作时间
    val operateAccountCid: String,  // 生成证号、废止等操作人
    val respContent: String,
    val ecertificateNo: String,     // 电子证书编号
    val status: String,             // 申请中、有效、年审中、废止  枚举：EcertificateStatus
    val validityPeriod: Int         // 有效期，以年为单位 ,默认为 5
) : Parcelable

@Parcelize
data class PostExamineAnswer(
    val answerList: List<Answer>,
    val cid: String,
    val createTime: Long
) : Parcelable


enum class PostExamineQuestionType(val string : String){

    TEXT("文本"),
    TEXTIMG("文本图片"),
    SELECT("单选"),
    MULTIPLE("多选"),
    IMG("图片");
}

data class SubmitRequest(
    val responseMan: String,
    val responseManId: String,
    val responseResult: String,
    val responseTime: Long,
    val statusRequest: String
)

@Parcelize
data class BankInfo(
    val _id: String,
    val bankName: String,//银行名称
    val cardNo: String,//银行卡号
    val createdTime: Long
) : Parcelable