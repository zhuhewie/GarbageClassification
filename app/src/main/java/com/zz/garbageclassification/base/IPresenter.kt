package com.zz.garbageclassification.base

interface IPresenter<in T : IBaseView> {
    fun attchView(view : T)
    fun unAttchView()

}