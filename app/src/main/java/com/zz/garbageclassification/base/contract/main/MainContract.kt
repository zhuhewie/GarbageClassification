package com.zz.garbageclassification.base.contract.main

import com.zz.garbageclassification.base.IBaseView
import com.zz.garbageclassification.base.IPresenter
import com.zz.garbageclassification.model.bean.response.GarbageResponse

interface MainContract {
    interface View : IBaseView {
        fun getError(errorMess : String)
        fun getSucc(grabage : GarbageResponse)

    }

    interface Presenter : IPresenter<View> {
        fun getGarbageClass(grabageName: String)
    }
}