package com.zz.garbageclassification.presenter.main

import android.content.Context
import android.content.Intent
import android.util.Log
import com.zz.garbageclassification.base.BasePresenter
import com.zz.garbageclassification.base.contract.main.MainContract
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.bean.response.NewsTitle
import com.zz.garbageclassification.model.http.model.LoginModel
import com.zz.garbageclassification.model.http.model.MainModel
import com.zz.garbageclassification.util.RxUtil
import com.zz.garbageclassification.util.StartUtil

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/13 <p>
 * <p> 更改时间 : 2019/3/13 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    private val mainModel by lazy { LoginModel() }
    private val TAG :String = "MainPresenter"
    override fun getGarbageClass(grabageName: String) {
        addSubscription(
            mainModel.getGarbageClass(grabageName)
                .subscribe({
                    Log.e(TAG,"${it.toString()}")
                    mRootView?.apply {
                        getSucc("")
                    }

                }, {
                    Log.e(TAG,"${it.message}")
                    mRootView?.apply {
                        getError(RxUtil.handleResult(it))
                    }

                })
        )
    }


}