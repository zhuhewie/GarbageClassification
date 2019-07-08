package com.zz.garbageclassification.widget

import android.text.TextUtils
import com.zz.garbageclassification.base.IBaseView
import com.zz.garbageclassification.model.http.ApiException
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException

class CommonSubscriber<T>(view : IBaseView) : ResourceSubscriber<T>() {

    constructor(view: IBaseView, errorMess : String) : this(view) {
        this.mErrorMsg = errorMess
    }
    constructor(view: IBaseView, isShowErrorState : Boolean) : this(view) {
        this.isShowErrorState = isShowErrorState
    }
    constructor(view: IBaseView, errorMess: String, isShowErrorState : Boolean) : this(view) {
        this.mErrorMsg = errorMess
        this.isShowErrorState = isShowErrorState
    }

    private var mView: IBaseView? = null
    private var mErrorMsg: String? = null
    private var isShowErrorState = true
    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(t: Throwable?) {
        if (mView != null) {
            if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
//            mView!!.showErrorMsg(mErrorMsg!!)
            } else if (t is ApiException) {
//            mView!!.showErrorMsg(t.toString())
            } else if (t is HttpException) {
//            mView!!.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ")
            } else {
//            mView!!.showErrorMsg("未知错误ヽ(≧Д≦)ノ")
//            LogUtil.d(t.toString())
            }
            if (isShowErrorState) {
//            mView!!.stateError()
            }
        }

    }
}