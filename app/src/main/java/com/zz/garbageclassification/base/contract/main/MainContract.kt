package com.zz.garbageclassification.base.contract.main

import com.zz.garbageclassification.base.IBaseView
import com.zz.garbageclassification.base.IPresenter
import com.zz.garbageclassification.model.bean.response.ArbitratorResponse
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.bean.response.PersonMaterResponse

interface MainContract {
    interface View : IBaseView {
        fun getError(errorMess : String)
        fun getSucc(mess : String)

    }

    interface Presenter : IPresenter<View> {
        fun getGarbageClass(grabageName: String)
    }
}