package com.zz.garbageclassification.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T : IBaseView> : IPresenter<T> {
    var mRootView: T? = null
        set(value) {
            field = value
        }

    private var compositeDisposable = CompositeDisposable()


    override fun attchView(view: T) {
        this.mRootView = view
    }

    override fun unAttchView() {
        mRootView = null

        //保证activity结束时取消所有正在执行的订阅
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttched() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }



    fun addSubscription(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}