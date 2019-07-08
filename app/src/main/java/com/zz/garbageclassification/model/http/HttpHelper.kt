package com.zz.garbageclassification.model.http

import com.zz.garbageclassification.model.bean.response.AccountTokenResponse
import io.reactivex.Flowable

interface HttpHelper {

    fun postAccountToken(loginName : String, password : String) : Flowable<AccountTokenResponse>
}