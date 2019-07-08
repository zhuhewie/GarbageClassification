package com.zz.garbageclassification.model.http.model

import android.util.Log
import com.zz.garbageclassification.Constants
import com.zz.garbageclassification.app.App
import com.zz.garbageclassification.model.bean.Token
import com.zz.garbageclassification.model.bean.response.AccountTokenResponse
import com.zz.garbageclassification.model.bean.response.CheckTokenResponse
import com.zz.garbageclassification.model.bean.response.LoginAuthCodeResponse
import com.zz.garbageclassification.model.bean.response.ResetAuthCodeResponse
import com.zz.garbageclassification.model.http.RetrofitManager
import com.zz.garbageclassification.model.rx.RetryLogin
import com.zz.garbageclassification.util.ConcealUtil
import com.zz.garbageclassification.util.RxUtil
import com.google.gson.Gson
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody

class LoginModel {

    fun getGarbageClass(garbage:String) :Flowable<ResponseBody>{
        return RetrofitManager.loginService


            .getGarbageClass(garbage)
            .retryWhen(RetryLogin())
            .compose(SchedulerUtils.ioToMain())
    }
    fun loginByAccount(
        userName: String,
        password: String
    ): Flowable<AccountTokenResponse> {
        return RetrofitManager.loginService.getAccountToken(userName, password)
            .doOnNext(Consumer {
                //缓存token
                ConcealUtil.enypto(App.instance, Constants.CONCEAL_KEY, Gson().toJson(it), Constants.TOKEN)
                //缓存账号
                ConcealUtil.enypto(
                    App.instance,
                    Constants.CONCEAL_USER_KEY,
                    userName + Constants.SEPARATOR + password,
                    Constants.USER
                )
                Token.instance.copy(it)
                Log.d("Okhttp", "缓存登录信息")
            })
    }

    /**
     * 手机验证码 登录获取token
     *
     *
     */
    fun login(
        authorizeId : String ,
        captcha : String ,
        mobile : String): Flowable<AccountTokenResponse>{
        return RetrofitManager.loginService.getMobileToken(authorizeId, captcha,mobile)
            .doOnNext {
                Token.instance.copy(it)
                ConcealUtil.enypto(App.instance,Constants.CONCEAL_KEY,Gson().toJson(it),Constants.TOKEN)
            }
            .compose(SchedulerUtils.ioToMain())


    }

    /**
     * 验证token是否有效
     */
    fun checkToken(
        accessToken : String
    ) :Flowable<CheckTokenResponse>{
        return RetrofitManager.loginService.checkToken(accessToken)
            .compose(SchedulerUtils.ioToMain())
    }


    /**
     * 登录 发送手机验证码
     */
    fun captcha(mobile : String) : Flowable<LoginAuthCodeResponse>{
        return RetrofitManager.loginService.captcha("000002",mobile)
            .compose(SchedulerUtils.ioToMain())
    }


    /**
     * 注册账号发送验证码
     */
    fun registerSendVeriCode(phone : String) :Flowable<ResponseBody>{
        return RetrofitManager.loginService.registerSendVerificationCode(phone)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 验证验证码是否正确
     */
    fun verify(guid:String, code:String) :Flowable<ResponseBody>{
        return RetrofitManager.loginService.verify(guid,code)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 待办 - 验证码 确认签名
     */
    fun confirm(
        guid: String,
        code: String,
        docType: String,
        mainInfoCid: String,
        opinion: String
    ):Flowable<ResponseBody>{
        return RetrofitManager.loginService.confirm(guid,code,docType,mainInfoCid,opinion)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 重置密码---验证验证码
     */
    fun verifyResetPsw(guid:String, code:String) :Flowable<ResponseBody>{
        return RetrofitManager.loginService.registerByMobile(guid,code)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 通过手机号码 注册账号 设置密码
     */
    fun registerByMobile(phone : String ,password : String) :Flowable<ResponseBody> {
        return RetrofitManager.loginService.registerByMobile(phoneNumber = phone,password = password)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 通过手机号码 邮箱,重置密码 获取验证码
     */
    fun getResetAuthCode(type: String, subject: String): Flowable<ResetAuthCodeResponse> {
        return RetrofitManager.loginService.sendByType(type, subject)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 重置密码
     */
    fun resetPwd(
        account:String,
        password:String,
        guid:String,
        code:String
    ) :Flowable<ResponseBody>{
        return RetrofitManager.loginService.resetPwd(
            account,password,guid,code
        )
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 修改密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return
     */
    fun changePwd(oldPwd: String, newPwd: String): Flowable<ResponseBody> {
        return Flowable.defer(RxUtil.getToken())
            .flatMap {
                RetrofitManager.loginService.changePwd(
                    it,
                    oldPwd,
                    newPwd
                )
            }
            .retryWhen(RetryLogin())
            .compose(SchedulerUtils.ioToMain())
    }


}