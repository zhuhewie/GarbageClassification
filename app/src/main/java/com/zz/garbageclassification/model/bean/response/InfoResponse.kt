package com.zz.garbageclassification.model.bean.response

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.zz.garbageclassification.model.bean.eume.AuditEnum
import com.zz.garbageclassification.model.bean.update.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * <p> 文件描述 : 获取仲裁员信息的返回体<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/21 <p>
 * <p> 更改时间 : 2019/3/21 <p>
 * <p> 版本号 : 1 <p>
 *
 *
 */
/**

 */

@Parcelize
data class AttaFilesPath(
    val appHeadIcon: String,
    val ecertificatePath: String,
    val officialqrcode: String,
    val opinionPath: String,
    val promisePath: String,
    val signedPhoto: String
) : Parcelable

@Parcelize
data class Arbitrator(
    val _id: String,
    val accountCid: String,         //关联帐号ID
    val address: String,        //兼容易简 ARBMIS 的坑
    val zipCode: String,            //仲裁员首先地址邮编
    val zone: String,               //片区
    val achievements: String,       //主要专业成果(学术成果)
    val arbitratorQualificationList: List<ArbitratorQualificationInfo>,//仲裁员任职资格
    val bachelorDegree: String,     //学位
    val bornDate: String,           //出生日期
    val cardNo: BankInfo?,          //银行账户
    val city: String,                //所在城市
    val collegeGraduate: String,     //学历
    val commissionId: String,
    val company: String,            //工作单位
    val companyAddress: String,     //单位地址
    val companyFax: String,          //公司传真号码
    val companyIdea: String,        //单位意见
    val companyPhone: String,       //单位电话
    val companyPostalCode: String,   //单位邮编
    val companySignDate: String,
    val createAccountCid: String,   //创建帐号
    val createType: String,         //创建类型，注册，系统同步
    val remark: String,        //备注
    val createdTime: Long,
    val dutyStatus: String,         //在职状态，在职或离、退休
    val educationInfoList: List<EducationInfo>,
    val emails: String,              //邮箱
    val ethnicGroup: String,            //民族
    val expertIn: String,               //擅长专业
    val foreignLanguage: String,//外语水平
    val gender: String,         //性别
    val graduationSchool: String,//毕业院校
    val health: String,             //健康状态
    val homeAddress: String,        //住址
    val homePhone: String,           //住址电话
    val homePostalCode: String,     //邮编
    val hoursOfYear: String,        //每年有多少时间从事兼职仲裁工作
    val identityNo: String,             //证件号码
    val identityType: String,           //证件类型
    val insideOrder: Int,
    val isInside: String,
    val languageInfoList: List<LanguageInfo>,
    val major: String,       //所学专业
    val maritalStatus: String,
    val haveMate: String,        //是否已婚
    val mateCompany: String,    //配偶工作单位
    val mateContactWay: String, //配偶联系方式
    val mateGender: String,     //配偶姓别
    val mateName: String,       //配偶姓名
    val matePost: String,       //配偶 职务
    val matePhone: String,      //配偶电话
    val mobiles: String,        //手机
    val name: String,           //姓名
    val namePinYin: String,             //名字拼音
    val namePinYinAcronym: String,      //名字拼音首字母
    val nation: String,                 //籍贯
    val occupationCategory: String,     //职业分类
    val otherOccupationCategory: String,// 其他的职业分类
    val otherContactWay: String,        //其他联系方式
    val personCid: String,
    val politicalStatus: String,         // 政治面貌
    val post: String,                   //职务
    val preferredAddressType: String,       //首选通讯地址类型
    val otherAddress: String,               //其他通讯地址.
    val professionalTitle: String,
    val signPhotoAuditMsg: SignPhotoAuditMsg,
    val signedPhoto: SignedPhoto,           //签名图片
    val signedPhotoStatus: String,
    val status: String,     //草稿、已提交、审核通过
    val telephone: String,
    val trustsignInfo: TrustsignInfo,
    val updateTime: Long,            //更新时间
    val usedName: String,           //曾用名
    val weChat: String?,             // 微信号
    val workAchievement: String?,               // 主要工作业绩
    @SerializedName("workHistorieList")
    val workHistorieInfoList: MutableList<WorkHistorieInfo>     //与仲裁相关的培训或者工作经历
) : Parcelable

@Parcelize
data class TrustsignInfo(
    val cardNo: String,
    val cardType: String,
    val createTime: Long,
    val signId: String,
    val signStatus: String
) : Parcelable


@Parcelize
data class InfoResponse(
    val arbitrator: Arbitrator,         //仲裁员
    val attaFilesPath: AttaFilesPath,
    val commissions: List<Commission>,
    val ecertificateStatus: String,
    val headIconPath: String,
    val modifyAuditList: ModifyAuditList
) : Parcelable

@Parcelize
data class ModifyAuditList(
    val achievements: ArbitratorModifyAudit,                        //"主要学术成果"
    val arbitratorQualificationList: List<ArbitratorQualificationInfo>,//符合仲裁员何种条件 ;仲裁员任职资格
    val bachelorDegree: ArbitratorModifyAudit,                      //学位
    val cardNo: ArbitratorModifyAudit,                              //"报酬接收账号"
    val company: ArbitratorModifyAudit,                             //工作单位
    val collegeGraduate: ArbitratorModifyAudit,                     //学历
    val compString: String,
    val educationInfoList: MutableList<ArbitratorModifyAudit>,      //教育信息
    val expertIn: ArbitratorModifyAudit,                            //擅长的专业
    val languageInfoList: MutableList<ArbitratorModifyAudit>,       //外语能力
    val tagListInfo: List<String>,                                  //标签
    val occupationCategory: ArbitratorModifyAudit,
    val professionalTitle: ArbitratorModifyAudit,                   //职称
    val workAchievement: ArbitratorModifyAudit,                     // 主要工作业绩
    val workHistorieList: MutableList<ArbitratorModifyAudit>        //"培训经历"

) : Parcelable

@Parcelize
data class ArbitratorModifyAudit(
    @SerializedName("_id")
    val  cid                    :String,                //主键
    val  arbitratorCid          :String,                // 仲裁员cid
    val  arbitratorName         :String,                // 仲裁员名字
    val  collegeGraduate        :String,                // 学历
    val  bachelorDegree         :String,                // 学位
    val  company                :String,                // 工作单位
    val  professionalTitle      :String,                // 职称
    val  occupationCategory     :String,                // 职业分类
    val  otherOccupationCategory:String,                // 其他的职业分类
    val  expertIn               :String,                // 擅长专业
    val  achievements           :String,                // 主要专业成果
    val  workAchievement        :String,                // 主要工作业绩
    val  bankName               :String,                // 银行名称
    val  cardNo                 :String,                // 银行卡号
    val  commissionId           :String,                // 对应那个机构的银行卡信息
    val  modifyField            :String,                // 修改字段
    val  modifyFieldNames       :String,                // 待修改字段名称
    val  auditAccountCid        :String,                // 审核账号
    val  auditStatus            :String,                // 审核状态
    val  auditResponse          :String,                // 审核反馈
    val createDate              :Long,                  // 创建时间
    val auditDate               :Long,                  // 审核时间

    val workHistorieList        :WorkHistoryAudit,      // 与仲裁相关的工作或培训经历
    val educationInfoList       :EducationInfoAudit,    // 教育背景
    val languageInfoList        :LanguageInfoAudit      // 语言
) : Parcelable





@Parcelize

data class SignPhotoAuditMsg(
    val auditResponse: AuditResponse,
    val submitSignPath: String
) : Parcelable

@Parcelize
data class AuditResponse(
    val applyStatus: String,// 签名申请状态 ：待审核，通过，不通过 ,同步arbitrator 的 signedPhotoStatus
    val requestMan: String, //提交人
    val requestManId: String,   //提交人账号id
    val requestTime: String,    //提交签名时间
    val responseMan: String,
    val responseManId: String,
    val responseResult: String,
    val responseTime: Long,
    val statusRequest: String
) : Parcelable




/**
 * 与仲裁相关的工作或者培训经历
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class WorkHistorieInfo(
    var relatedArbitration: Boolean,    //与仲裁相关
    var relatedMediation: Boolean,      //与调解相关
    var relatedOtherSociety: Boolean,   //与其他社团相关
    var relatedWork: Boolean           //工作履历
) : WorkHistorieBase() ,Parcelable
/**
 * 审核中 - 与仲裁相关的工作或者培训经历
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class WorkHistoryAudit (
    var auditStatus         :String ,           // 状态
    var oldCid              :String ,           // 新增时为空，修改时为原相关的cid
    var changeType          :String             // 新增、修改
) : WorkHistorieBase(),Parcelable
/**
 * 基类 - 与仲裁相关的工作或者培训经历
 */
@SuppressLint("ParcelCreator")
@Parcelize
open class WorkHistorieBase : Parcelable {
    @SerializedName(value = "_id",alternate = ["cid"])
    var cid: String? = null
    var company: String? = null              //工作单位
    var workContent: String?= null           // 工作内容
    var startDate: String?=null              //开始时间
    var endDate: String?=null                //结束时间
    var certificate: String?=null            //获得证书
    var createdTime: Long? = 0
    var post: String?=null                   //职务

}




/**
 * 仲裁机构的信息
 * "applicationFormFile":{
"uploader":"hi@gzyijian.com",
"uploadTime":1546590428774,
"fileName":"申请人证据1（合同）.pdf",
"storageCode":"group1/M01/25/F6/rBRk4VwvGNyAFUKFAARpwAiuVYk5913956"
}
 */
@Parcelize
data class Commission(
    val addComponents: Int,
    val addDefaultPortal: Int,
    val address: String,        //兼容易简 ARBMIS 的坑
//    val applicationFormFile: String,
    val applyComments: String,      //申请内容
    val applyDateTime: Long,        //申请时间
    val applySignName: String,      //申请人签名
    val applyType: String,          // 申请类型 ： 临时仲裁员、机构仲裁员
    val arbitratorCid: String,      //仲裁员ID
    val arbitratorId: String,       //机构仲裁员ID
    val arbitratorNo: String,
    val bankInfo: BankInfo,         //银行帐户信息：
    val cid: String,                //主键
    val commissionId: String,        //仲裁委id
    val commissionName: String,     // 仲裁委名称
    val ecertificateApply: EcertificateApply, // 仲裁员电子证书申请信息
    val email: String,
    val hasCertificate: Boolean,    //是否有电子证书
    val icCardNo: String,           // ID卡号
    val mobile: String,
    val notPassCount: String,           // 审核不通过次数
    val passTime: Long,
    val postExamineAnswer: PostExamineAnswer,// 广仲的任职资格调查信息
    val promise: String,
    val promiseSignName: String,
    val promiseTime: String,
    val refuseSubmitTime: Long,
    val respAccountName: String, //审核人签名
    val respComments: String,       //审批意见
    val respHistoryList: List<String>, // 审核历史
    val respTime: Long,             //审批时间
    val sourceArbitratorId: String, //来源仲裁员Id
    val sourceCommissionName: String,//来源仲裁委
    val sourceName: String,         //来源系统名称，可以为空
    val sourcePrimaryKey: String,    //来源仲裁员主键
    val status: String,     // //审核状态：APPLYING("申请中"),NOPASS("预审通过"),AUDITPASS("通过"),EMPLOYED("在聘"),FIRED("解聘"),REFUSE("不通过")
    val submitRequest: List<AuditResponse>,
    val submitType: String,// 提交申请的类型： PC or APP
    val switchArbitrateCapable: String,//适合应聘何种仲裁能力
    val telephone: String
) : Parcelable




data class ArbitratorQualification(
    val _id: String,
    val assignedDate: String, // 授予时间
    val certificateNo: String,      //文凭编号
    val createdTime: Long,
    val organization: String,
    val professionalTitle: String,//专业技术职称
    val startDate: String,// // 开始时间
    val endDate: String,// // 结束时间
    val type: String,
    val typeText: String,
    val lawyerCardNo: String //// 律师证件号码
)