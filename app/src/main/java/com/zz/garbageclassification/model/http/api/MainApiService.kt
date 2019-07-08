package com.zz.garbageclassification.model.http.api

import com.zz.garbageclassification.model.bean.response.*
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 *
 */
interface MainApiService {


    /**
     * 检查更新
     *
     */
    @GET("api/appupdate/checkversion/")
    fun update(
        @Query("version") version: String,
        @Query("platform") platform: String
    ): Flowable<ResponseBody>

    @FormUrlEncoded
    @POST("api/user/changePwd/")
    fun changePwd(
        @Header("Authorization") authorization:String,
        @Field("oldPwd") oldPwd : String ,
        @Field("newPwd") newPwd : String
    ): Flowable<ResponseBody>


////////////////////////////////////////首页///////////////////////////////////////////////
    /**
     * 首页 - 微信动态列表
     * length 返回条数，后台默认是 8
     * currentPage 当前页码数
     */
    @GET("api/newRelease/getWechatNews/")
    fun getWechatNews(
        @Header("Authorization") authorization: String,
        @Query("length") length: String,
        @Query("currentPage") currentPage: String
    ): Flowable<NewsResponse>

    /**
     * 首页 - 新闻动态列表
     * length 返回条数，后台默认是 8
     * currentPage 当前页码数
     */
    @GET("api/newRelease/getNormalNews/")
    fun getNews(
        @Header("Authorization") authorization: String,
        @Query("length") length: String,
        @Query("currentPage") currentPage: String
    ): Flowable<NewsResponse>


    /**
     * 仲裁员身份、信息提交状态
     */
    @GET("api/mobile/arbitrator/initmsg/")
    fun getArbitratorMsg(
        @Header("Authorization") authorization: String
    ): Flowable<ArbitratorResponse>

//  /**
//   * 获取仲裁员申请情况列表
//   */
//  @GET("api/mobile/arbitrator/applyList/")
//  fun getApplyList(
//    @Header("Authorization") authorization:String,
//    @Query("commissionName") commissionName:String
//
//  ) : Flowable<ArbitratorResponse>
//


    /**
     * 首页 列表 和  首页-我的 - 菜单列表
     * https://www.coaacloud.com/arbitrator/api/mobile/menus?pageType=首页&platform=IOS&version=1.6.0
     */
    @GET("api/mobile/menus/")
    fun getMenus(
        @Header("Authorization") authorization: String,
        @Query("pageType") pageType: String,
        @Query("version") version: String,
        @Query("platform") platform: String
    ): Flowable<MutableList<MenusResponse>>

    /**
     * 首页 - 我的 - 获取仲裁员信息
     */
    @GET("api/mobile/arbitrator/basemsg/")
    fun getInfo(
        @Header("Authorization") authorization: String
    ): Flowable<InfoResponse>
    /**
     * 首页 - 我的 - 获取仲裁员信息
     */
    @GET("api/mobile/arbitrator/basemsg/")
    fun getInfoBody(
        @Header("Authorization") authorization: String
    ): Flowable<ResponseBody>


    /**
     * 首页 - 我的 - 获取仲裁员基本信息
     */
    @GET("api/mobile/personalInfo/")
    fun getBasicInfo(
        @Header("Authorization") authorization: String
    ): Flowable<BasicInfoResponse>

    /**
     * 首页 - 我的 - 庭室导航列表
     */
    @GET("api/addressNavigation/list/")
    fun getAddressNavigationList(
        @Header("Authorization") authorization: String
    ): Flowable<List<AddressResponse>>

    /**
     * 首页 - 待办 - 仲裁员待签名及已签名案件列表
     * signed 签名，true 已签名 ;false 待签名
     * pageSize 返回条数，
     * url 请求地址，
     * currentPage 当前页码数
     */
    @GET("api/arbitratorSign/list/")
    fun getSignList(
        @Header("Authorization") authorization: String,
        @Query("signed") signed: Boolean,
        @Query("pageSize") pageSize: String,
        @Query("currentPage") currentPage: String
    ): Flowable<SignCaseResponse<SignCase>>

    /**
     * 首页 - 待办 - 仲裁员待签名及已签名案件列表
     * signed 签名，true 已签名 ;false 待签名
     * pageSize 返回条数，
     * url 请求地址，
     * currentPage 当前页码数
     */
    @GET("api/arbitratorSign/readSigningPdf/")
    fun readPdf(
        @Header("Authorization") authorization: String,
        @Query("mainInfoCid") mainInfoCid: String,
        @Query("docType") docType: String
    ): Flowable<ResponseBody>


    /**
     * 首页 - 待办 - 仲裁员待签名及已签名案件列表
     * bookStatus 签名，状态：待开庭 \ 已开庭
     * pageSize 返回条数，默认 10
     * url 请求地址，
     * currentPage 当前页码数 	当前页 ，默认 1
     */
    @GET("api/courtRoomBook/page/")
    fun getSessionList(
        @Header("Authorization") authorization: String,
        @Query("bookStatus") signed: String,
        @Query("pageSize") pageSize: String,
        @Query("currentPage") currentPage: String
    ): Flowable<SignCaseResponse<SessionCase>>


    /**
     * 首页 - 待办 - 文书签名
     */
    @POST("api/arbitratorSign/sendSms")
    fun sendSms(
        @Header("Authorization") authorization:String
        ):Flowable<SignAuthCodeResponse>
////////////////////////////////////////通用列表界面///////////////////////////////////////////////

    /**
     * 通用列表页 - 获取列表信息
     * length 返回条数，
     * url 请求地址，
     * currentPage 当前页码数
     */
    @GET
    fun getMessList(
        @Url url: String,
        @Header("Authorization") authorization: String,
        @Query("length") length: String,
        @Query("currentPage") currentPage: String
    ): Flowable<NewsResponse>


    /**
     * 用户反馈
     */
    @Multipart
    @POST("api/mobile/feedBack/feedBackContent/")
    fun uploadFeedBack(
        @Header("Authorization") authorization: String,
        @Part("feedBackContent") feedBackContent: RequestBody,
        @Part("contact") contact: RequestBody,
        @Part args: List<MultipartBody.Part>

    ): Flowable<ResponseBody>


    /**
     *  获取案件列表
     */
    @GET("api/mobile/v3/caseInfo/findArbitratorCasesByPage/")
    fun getCaseList(
        @Header("Authorization") authorization: String,
        @QueryMap paramMap: Map<String, String>
    ): Flowable<CaseResponse>


    /**
     *  获取案件详细信息
     */
    @GET("api/mobile/{mainInfoCid}/caseInfo/")
    fun getCaseInfo(
        @Header("Authorization") authorization: String,
        @Path("mainInfoCid") mainInfoCid: String
    ): Flowable<CaseDetailResponse>


    /**
     *  获取消息类型列表
     */
    @GET("api/mobile/messagetype/list/")
    fun getMessageTypeList(
        @Header("Authorization") authorization: String
    ): Flowable<MessageTypeReponse>

    /**
     *  T
     *  获取案件案号 办案秘书电话信息
     */
    @GET("api/mobile/2018v2/getCancelCaseInfo/{mainInfoCid}/")
    fun getCancleCaseInfo(
        @Header("Authorization") authorization: String,
        @Path("mainInfoCid") mainInfoCid: String

    ): Flowable<CancleCaseResponse>


    /**
     *  获取消息列表
     */
    @GET("api/mobile/message/list/")
    fun getMessageList(
        @Header("Authorization") authorization: String,
        @Query("messageType") messageType: String,
        @Query("length") length: String,
        @Query("currentPage") currentPage: String,
        @Query("readStatus") readStatus: String
    ): Flowable<MessageResponse>
    /**
     *  消息标志为已读（点开消息阅读时调用）
     */
    @GET("api/mobile/message/read/{remindCid}/")
    fun readMess(
        @Header("Authorization") authorization: String,
        @Path("remindCid") remindCid: String
    ): Flowable<ResponseBody>


    /**
     *  分月统计案件数量
     *  caseType 三种 :"WEBCASE" "NONWEBCASE" ""
     */
    @GET("api/mobile/2018v3/arbitratorCaseCount/")
    fun getCaseCount(
        @Header("Authorization") authorization: String,
        @Query("caseYear") caseYear: String,
        @Query("caseType") length: String
    ): Flowable<CaseCountResponse>

    /**
     *  获取饼图统计数据
     *  caseYear : 2018
     */
    @GET("api/mobile/2018v2/arbitratorCaseStatsForPie/")
    fun getCaseStatsForPie(
        @Header("Authorization") authorization: String,
        @Query("caseYear") caseYear: String
    ): Flowable<CaseStatsResponse>


    /**
     *  获取仲裁员申请情况列表
     *
     */
    @GET("api/mobile/arbitrator/applyList/")
    fun getApplyList(
        @Header("Authorization") authorization: String,
        @Query("commissionName") commissionName: String
    ): Flowable<List<PersonMaterResponse>>

    /**
     *  获取仲裁员签名审核信息资料
     *
     */
    @GET("api/signPhoto/auditStatus/")
    fun getAuditStatus(
        @Header("Authorization") authorization: String
    ): Flowable<AuditStatusResponse>

    /**
     * 获取仲裁员提交签名信息
     */
    @GET("api/signphoto/getSignRequest/")
    fun getSignRequest(
        @Header("Authorization") authorization: String,
        @Query("commissionId") commissionId: String

    ): Flowable<AuditStatusResponse>

    /**
     * 上传签名----base64
     */
    @FormUrlEncoded
    @POST("api/arbitratorSign/save")
    fun uploadSignPhoto(
        @Header("Authorization") authorization: String,
        @Field("signData") signData: String
    ): Flowable<ResponseBody>

    /**
     * 保存APP头像
     *
     */
    @Multipart
    @POST("api/mobile/account/uploadHeadIcon/")
    fun uploadAvatar(
        @Header("Authorization") authorization: String,
        @Part avatar: MultipartBody.Part
    ): Flowable<ResponseBody>

    /**
     * 上传仲裁员资料头像
     *
     */
    @Multipart
    @POST("api/mobile/arbitrator/uploadHeadIcon/")
    fun uploadArbAvatar(
        @Header("Authorization") authorization: String,
        @Part avatar: MultipartBody.Part
    ): Flowable<ResponseBody>


    /**
     * 保存调查问卷答案----json 格式接收答案内容
     *
     */
    @Multipart
    @POST("api/postExamine/saveansStr/")
    fun saveansStr(
        @Header("Authorization") authorization: String,
        @Part answer: MultipartBody.Part,
        @Part commissionCid: MultipartBody.Part,
        @Part holdFilePaths: List<MultipartBody.Part>,
        @Part avatar: List<MultipartBody.Part>
    ): Flowable<ResponseBody>


    /**
     *  获取申请机构仲裁员调查问卷--问题列表
     *
     */
    @GET("api/postExamine/questionList/")
    fun getQuestionList(
        @Header("Authorization") authorization: String,
        @Query("commissionCid") commissionCid: String
    ): Flowable<QuestionResponse>

    /**
     *  获取仲裁员任职情况--答案信息
     *
     */
    @GET("api/postExamine/answer/")
    fun getAnswerList(
        @Header("Authorization") authorization: String,
        @Query("commissionCid") commissionCid: String
    ): Flowable<AnswerResponse>

    /**
     *  获取仲裁机构信息列表
     *
     */
    @GET("api/mobile/arbitrator/organizationList/")
    fun getCommissionList(
        @Header("Authorization") authorization: String
    ): Flowable<List<CommissionResponse>>

    /**
     * 提交申请（临时 / 机构）
     */
    @FormUrlEncoded
    @POST("api/mobile/arbitrator/submitArbitratorApply/")
    fun submitApply(
        @Header("Authorization") authorization: String,
        @Field("applyType") applyType: String,
        @Field("commissionId") commissionId: String,
        @Field("commissionName") commissionName: String,
        @Field("applyComments") applyComments: String,
        @Field("promise") promise: String,
        @Field("submitType") submitType: String
    ): Flowable<ResponseBody>

    /**
     * 完善资料-保存无附件信息接口
     * 仲裁员基本信息修改--无需审核
     */
    @FormUrlEncoded
    @POST
    fun saveBasicMsg(
        @Url url: String,
        @Header("Authorization") authorization: String,
        @FieldMap info :Map<String,String>
    ): Flowable<ResponseBody>


    /**
     * 保存仲裁员任职资格
     */
    @Multipart
    @POST("api/mobile/completing/arbitratorQualificationInfo/")
    fun saveQualiInfo(
        @Header("Authorization") authorization: String,
        @Part info :List<MultipartBody.Part>,
        @Part files: List<MultipartBody.Part>,
        @Part holdFilePaths: List<MultipartBody.Part>
    ): Flowable<ResponseBody>

    /**
     * 完善资料---保存外语能力
     * 修改 --- 外语能力提交修改申请
     */
    @Multipart
    @POST
    fun saveLanguageInfo(
        @Url url : String,
        @Header("Authorization") authorization: String,
        @Part info :List<MultipartBody.Part>,
        @Part files: List<MultipartBody.Part>,
        @Part holdFilePaths: List<MultipartBody.Part>
    ): Flowable<ResponseBody>
    /**
     * 完善资料---保存外语能力
     * 修改 --- 外语能力提交修改申请
     */
    @Multipart
    @POST
    fun saveTemplate(
        @Url url : String,
        @Header("Authorization") authorization: String,
        @Part files: List<MultipartBody.Part>,
        @Part holdFilePaths: List<MultipartBody.Part>
    ): Flowable<ResponseBody>

    /**
     *  完善资料--保存学历和附件
     *  完善资料--保存学位信息
     *  完善资料--保存学位信息
     */
    @Multipart
    @POST
    fun completeMess(
        @Url url: String,
        @Header("Authorization") authorization: String,
        @PartMap mess: Map<String,String>,
        @Part holdFilePaths: List<MultipartBody.Part>,
        @Part flie: List<MultipartBody.Part>
    ): Flowable<ResponseBody>

    /**
     *  获取公共参数
     *
     *  fieldType =
     * PC_EXCEL_FIELD("仲裁员excel导出字段","pcExcelField"),// PC端导出excel , 修改字段
     * ARB_FIELD("仲裁员表单字段","arbFormField"),  // arbitrator 资料提交字段
     * EXPERTIN("擅长专业","expertIn"),// 擅长的专业选项
     * IDENTITYTYPE("证件类型","identityType"),// 证件类型
     * POLITICALSTATUS("政治面貌","politicalStatus"),// 政治面貌
     * COLLEGEGRADUATE("学历","collegeGraduate"),// 学历
     * PREFERREDADDRESSTYPE("首选通讯地址类型","preferredAddressType"),// 首选通讯地址类型
     * HAVEMATE("是否已婚","haveMate"),// 婚否
     * DUTYSTATUS("是否在职","dutyStatus"),// 是否在职
     * OCCUPATIONCATEGORY("职业分类","occupationCategory"),// 职业分类
     * ARB_CANNOT_CHANGE("仲裁员身份不能编辑字段","arbCannotEdit"); // 仲裁员身份不能修改的字段
     */
    @GET("api/common/fields/")
    fun getCommonField(
        @Header("Authorization") authorization: String,
        @Query("identity") identity: String,
        @Query("fieldType") fieldType: String
    ): Flowable<CommonFileResponse>


    /**
     *  获取附件文件列表
     */
    @GET("api/manageFile/folder/")
    fun getFolder(
        @Header("Authorization") authorization: String,
        @Query("path") path: String
    ): Flowable<List<String>>

    /**
     *  撤销某项资料修改申请
     */
    @GET("api/mobile/arbitratorModifyAudit/cancelApply/")
    fun cancelApply(
        @Header("Authorization") authorization: String,
        @Query("arbitratorModifyAuditCid") arbitratorModifyAuditCid: String,
        @Query("modifyField") modifyField: String
    ): Flowable<ResponseBody>

    /**
     * 删除资料中的list的一项
     * delKey : 外语、教育、工作中的cid , 或任职资格中的 TYPE_1 ---TYPE_5
     * delType : workHistorieList , educationInfoList , languageInfoList , arbitratorQualificationList (培训经历,教育经历，外语能力，任职资格枚举)
     */
    @FormUrlEncoded
    @POST("api/mobile/completing/delInfoList/")
    fun delInfoList(
        @Header("Authorization") authorization: String,
        @Field("delKey") delKey: String,
        @Field("delType") delType: String
    ): Flowable<ResponseBody>


    /**
     *  下载模板
     */
    @GET
    fun downloadTemplate(
        @Url url: String,
        @Header("Authorization") authorization: String
    ): Flowable<ResponseBody>


}