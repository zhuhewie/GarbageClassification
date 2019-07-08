package com.zz.garbageclassification.model.bean

import android.text.TextUtils
import com.zz.garbageclassification.Constants
import com.zz.garbageclassification.app.App
import com.zz.garbageclassification.model.bean.response.AccountTokenResponse
import com.zz.garbageclassification.model.bean.response.ImgUserParam
import com.zz.garbageclassification.util.ConcealUtil
import com.zz.garbageclassification.util.SPUtil
import com.zz.garbageclassification.util.StringUtil
import com.google.gson.Gson

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class Token private constructor(){

    init {
        refresh()
    }


    var accessToken: String? = ""

    var refreshToken: String? = ""

    var imUserParam: ImgUserParam? = null
    var accountCid: String? = ""
    var expiresIn: String? = ""



    companion object {
        public val instance : Token by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
            Token()
        }
    }


    fun isLogin() : Boolean{
        return !TextUtils.isEmpty(accessToken)
    }

    /**
     * 刷新token 从本地sp刷新
     */
    fun refresh() {
        val constantString = SPUtil.getInstance().getString(Constants.TOKEN)
        if (TextUtils.isEmpty(constantString)) {
            clear()
            return
        } else {
            val tokenString = ConcealUtil.decrypt(App.instance, Constants.CONCEAL_KEY, constantString)
            if (TextUtils.isEmpty(tokenString))  {
                clear()
                return
            }
            copy(Gson().fromJson<AccountTokenResponse>(tokenString, AccountTokenResponse::class.java))
        }
    }

    /**
     * 用于网络请求的token:Bearer + accessToken
     */
    fun getBeareeToken() : String{
        if (StringUtil.isEmpty(accessToken)) {
            refresh()
            return "Bearer $accessToken"
        } else {
            return "Bearer $accessToken"
        }
    }


    fun copy(token: AccountTokenResponse) {
        accessToken = token.accessToken
        refreshToken = token.refreshToken
        imUserParam = token.imUserParam
        accountCid = token.accountCid
        expiresIn = token.expiresIn
    }

    private fun clear(){
        accessToken = ""
        refreshToken = ""
        imUserParam = null
        accountCid = ""
        expiresIn = ""
    }


}