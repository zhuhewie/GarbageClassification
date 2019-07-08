package com.zz.garbageclassification.base.contract.main

import com.zz.garbageclassification.base.IBaseView
import com.zz.garbageclassification.base.IPresenter
import com.zz.garbageclassification.model.bean.response.ArbitratorResponse
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.bean.response.PersonMaterResponse

interface MainContract {
    interface View : IBaseView {
        fun arbitratorMsg(msg: ArbitratorResponse)
        fun getMsgError(errorMess : String)
        fun menus(menusList : List<MenusResponse>)
        fun getWechatNewsError(errorMess : String)
        fun applyList(applyList : List<PersonMaterResponse>)
        fun getApplyError(errorMess : String)

    }

    interface Presenter : IPresenter<View> {
        fun getArbitratorMsg()
        fun getMenus()
        fun getApplyList()
        fun getNews(length: String,current:String)
        fun getWechatNews(length: String,current:String)
    }
}