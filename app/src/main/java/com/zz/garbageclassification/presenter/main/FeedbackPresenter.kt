package com.zz.garbageclassification.presenter.main

import android.util.Log
import com.zz.garbageclassification.base.BasePresenter
import com.zz.garbageclassification.base.contract.main.FeedbackContract
import com.zz.garbageclassification.base.contract.main.MainContract
import com.zz.garbageclassification.model.http.model.LoginModel
import com.zz.garbageclassification.util.RxUtil

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/13 <p>
 * <p> 更改时间 : 2019/3/13 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class FeedbackPresenter : BasePresenter<FeedbackContract.View>(), FeedbackContract.Presenter {
    private val mainModel by lazy { LoginModel() }
    private val TAG :String = "FeedbackPresenter"
    override fun commitGarbageClass(grabageName: String,classifName :String) {
        addSubscription(
            mainModel.commitGarbageClass(grabageName,classifName)
                .subscribe({
                    Log.e(TAG,"${it.toString()}")
                    mRootView?.apply {
                    }

                }, {
                    Log.e(TAG,"${it.message}")
                    mRootView?.apply {
                    }

                })
        )
    }


}