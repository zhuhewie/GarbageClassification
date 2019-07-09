package com.zz.garbageclassification.model.http.api

import com.zz.garbageclassification.model.bean.ResponseBodyBean
import com.zz.garbageclassification.model.bean.response.GarbageResponse
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoginApiService {

    /**
     *
     */
    /**
     * 查询垃圾类型
     */
    @GET("api/query/rubbish/")
    fun getGarbageClass(
        @Query("keyword") phone:String
    ) : Flowable<ResponseBodyBean<GarbageResponse>>

  /**
     * 查询垃圾类型
     */
    @GET("api/query/setrubbish/")
    fun commitGarbageClass(
        @Query("sign") sign:String
    ) : Flowable<ResponseBodyBean<String>>



//    /**
//     * 通过账号密码获取token
//     */
//    @FormUrlEncoded
//    @POST("api/msite/accounttoken/")
//    fun getAccountToken(
//        @Field("loginName") loginName : String ,
//        @Field("password") password : String
//        ): Flowable<AccountTokenResponse>
//
//
//    /**
//     * 验证令牌是否有效
//     */
//    @FormUrlEncoded
//    @POST("api/msite/userinfo/")
//    fun checkToken(
//        @Field("access_token") accessToken : String
//        ): Flowable<CheckTokenResponse>
//
//
//    /**
//     * 通过账号密码获取token
//     *
//     */
//    @FormUrlEncoded
//    @POST("api/msite/mobile/token/")
//    fun getMobileToken(
//        @Field("authorize_id")  authorizeId : String ,
//        @Field("captcha")       captcha : String ,
//        @Field("mobile")        mobile : String
//        ): Flowable<AccountTokenResponse>
//
//    /**
//     * 登录 发送手机验证码
//     */
//    @FormUrlEncoded
//    @POST("api/msite/captcha/")
//    fun captcha(
//        @Field("client_id") clientId : String ,
//        @Field("mobile") mobile : String
//        ): Flowable<LoginAuthCodeResponse>
//
//    /**
//     * 注册账号---发送验证码
//     */
//    @GET("api/vCode/{mobile}/sendVerificationCode/")
//    fun registerSendVerificationCode(
//        @Path("mobile") phone:String
//    ) : Flowable<ResponseBody>
//
//    /**
//     * 注册账号、首次登录、切换账号登录---验证 验证码 是否正确
//     */
//    @GET("api/vCode/{guid}/{code}/verify/")
//    fun verify(
//        @Path("guid") guid:String,
//        @Path("code") code:String
//    ) : Flowable<ResponseBody>
//
//    /**
//     * 注册接口
//     */
//    @FormUrlEncoded
//    @POST("api/register/registerByMobile/")
//    fun registerByMobile(
//        @Field("phoneNumber") phoneNumber : String ,
//        @Field("password") password : String,
//        @Field("zoneCode") zoneCode : String = "86"
//    ): Flowable<ResponseBody>
//
//
//    /**
//     * 通过手机、邮箱重置密码---发送验证码
//     */
//    @GET("api/msite/{type}/{subject}/sendByType/")
//    fun sendByType(
//        @Path("type") type:String,
//        @Path("subject") subject:String
//    ) : Flowable<ResetAuthCodeResponse>
//
//
//    /**
//     * 重置密码---验证验证码
//     */
//    @FormUrlEncoded
//    @POST("api/msite/verify/")
//    fun registerByMobile(
//        @Field("guid") guid : String ,
//        @Field("code") code : String
//    ): Flowable<ResponseBody>
//
//    /**
//     * 待办---确认签名
//     */
//    @FormUrlEncoded
//    @POST("api/arbitratorSign/confirm/")
//    fun confirm(
//        @Field("guid") guid : String ,
//        @Field("code") code : String,
//        @Field("docType") docType : String,
//        @Field("mainInfoCid") mainInfoCid : String,
//        @Field("opinion") opinion : String
//    ): Flowable<ResponseBody>
//
//
//    /**
//     * 重置密码
//     */
//    @FormUrlEncoded
//    @POST("api/msite/resetPwd/")
//    fun resetPwd(
//        @Field("loginName") loginName : String ,
//        @Field("password") password : String ,
//        @Field("guid") guid : String ,
//        @Field("code") code : String
//    ): Flowable<ResponseBody>
//
//
//    /**
//     * changePwd
//     * 修改密码
//     */
//    @FormUrlEncoded
//    @POST("api/user/changePwd/")
//    fun changePwd(
//        @Header("Authorization") authorization:String,
//        @Field("oldPwd") oldPwd : String ,
//        @Field("newPwd") newPwd : String
//    ): Flowable<ResponseBody>



}