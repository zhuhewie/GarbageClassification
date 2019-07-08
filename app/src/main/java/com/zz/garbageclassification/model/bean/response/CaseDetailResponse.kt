package com.zz.garbageclassification.model.bean.response

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/1 <p>
 * <p> 更改时间 : 2019/4/1 <p>
 * <p> 版本号 : 1 <p>
 *
 */

data class CaseDetailResponse(
    val accuAgents  : List<AccuAgents>,               //申请人代理人（申请人律师等）
    val accusers    : List<Accuser>,                    //申请人
    val appeAgents  : List<AppeAgents>,               //被申请人代理人
    val appellees   : List<Appellee>,                  //被申请人
    val caseInfo    : CaseInfo,                         //案件详情
    val cliam       : Cliam,                               //请求（申请人提出）
    val conClaim    : ConClaim                          //反请求（被申请人提出）
)

data class AccuAgents(
    val name :String                                //代理人姓名
)

data class AppeAgents(
    val name :String                                //代理人姓名
)

data class CaseInfo(
    val assignedMan: String,        //办案秘书姓名
    val assignedManTelPhone: String,//办案秘书电话
    val caseArbitrators: String,    //仲裁庭
    val cid: String,                //案件唯一键
    val claimTotal: Double,         //争议金额：请求
    val courtRoomBookModelList: MutableList<CourtRoomBookMode>, //开庭的
    val closeTime: String,          //结案时间
    val commissionName: String,     //仲裁机构
    val conClaimTotal: Double,      //反请求金额
    val conClaimFee: Double,        //仲裁费的反请求费
    val claimFee: Double,           //仲裁费的请求费
    val displayNo: String,          //案号
    val nature: String,             //案由
    val status: String,             //案件进度
    val totalFee: Double            //总仲裁费用
)

/**
 * 申请人
 */
data class Accuser(
    val addressList: String,
    val cid: String,
    val emailList: String,
    val idCardNo: String,
    val idCardType: String,
    val identityNo: String,
    val identityType: String,
    val legalRepresent: String,
    val mobileList: String,
    val name: String,                   //姓名
    val nature: String,                 //自然人
    val sex: String,
    val telephone: String,
    val type: String
)

/**
 * 请求（申请人提出）
 */
data class Cliam(
    val changeFeeConfirm: String,
    val cid: String,
    val claimItemList: List<ClaimItem>,
    val claimMoney: Double,
    val conterClaim: Boolean,
    val courtFee: Double,
    val courtFeeConfirm: Double,
    val draft: Boolean,
    val infoStatus: String,
    val noMoneyClaim: Double,
    val operateFee: Double,
    val operateFeeConfirm: Double,
    val provideTime: Long,
    val reason: String,             //事实请求
    val submit: Boolean
)

data class ClaimItem(
    val chageFromId: String,
    val change: String,
    val changeClaimId: String,
    val changeToId: String,
    val cid: String,
    val content: String,                //反请求内容
    val conterClaim: Boolean,
    val itemMoney: Double,
    val itemType: String,
    val litigants: String,              //被申请人
    val new: Boolean,
    val noMoneyNumber: Double
)

/**
 * 被申请人
 */
data class Appellee(
    val addressList: String,
    val cid: String,
    val emailList: String,
    val idCardNo: String,
    val idCardType: String,         //身份证
    val identityNo: String,
    val identityType: String,       //营业执照
    val legalRepresent: String,     //胡斌（法定代表人）
    val mobileList: String,
    val name: String,               //姓名
    val nature: String,             //企业
    val sex: String,
    val telephone: String,
    val type: String
)

/**
 * 反请求（被申请人提出）
 */
data class ConClaim(
    val changeFeeConfirm: String,
    val cid: String,
    val claimItemList: List<ClaimItem>,
    val claimMoney: Double,
    val conterClaim: Boolean,
    val courtFee: Double,
    val courtFeeConfirm: Double,
    val draft: Boolean,
    val infoStatus: String,                 //草稿
    val noMoneyClaim: Double,
    val operateFee: Double,
    val operateFeeConfirm: Double,
    val provideTime: Long,
    val reason: String,                     //反请求的事实理由
    val submit: Boolean
)

data class CourtRoomBookMode(
    val cid: String,
    val useFor: String,
    val shareRoom: String,
    val courtOpenDate: String,
    val startTime: String,          //开庭的开始时间
    val endTime: String,            //开庭的结束时间
    val bookStatus: String,         //庭示状态
    val createTime: String,
    val remark: String

    )

interface CaseBean{

}