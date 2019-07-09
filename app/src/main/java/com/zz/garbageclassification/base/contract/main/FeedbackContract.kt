package com.zz.garbageclassification.base.contract.main

import com.zz.garbageclassification.base.IBaseView
import com.zz.garbageclassification.base.IPresenter
import com.zz.garbageclassification.model.bean.response.GarbageResponse

interface FeedbackContract {
    interface View : IBaseView {
        fun commitError(errorMess : String)
        fun commitSucc(grabage : String)

    }

    interface Presenter : IPresenter<View> {
        fun commitGarbageClass(grabageName: String,classifName :String)
    }
}