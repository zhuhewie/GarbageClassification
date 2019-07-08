package com.zz.garbageclassification.presenter.main

import android.content.Context
import android.content.Intent
import android.util.Log
import com.zz.garbageclassification.base.BasePresenter
import com.zz.garbageclassification.base.contract.main.MainContract
import com.zz.garbageclassification.model.bean.main.NewsItemBean
import com.zz.garbageclassification.model.bean.eume.ListMessEnum
import com.zz.garbageclassification.model.bean.eume.MenuEnum
import com.zz.garbageclassification.model.bean.main.ApplyItemBean
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.bean.response.NewsTitle
import com.zz.garbageclassification.model.http.model.MainModel
import com.zz.garbageclassification.util.RxUtil
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.view.apply.SelectArbitratorTypeActivity

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/13 <p>
 * <p> 更改时间 : 2019/3/13 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    private val mainModel by lazy { MainModel() }
    private val TAG :String = "MainPresenter"
    var length : Int = 3    //每页条目数
    var newsCurrent : Int = 1    //当前页数
    var wechatnewsCurrent : Int = 1    //当前页数
    override fun getArbitratorMsg() {
        addSubscription(
            mainModel.getArbitrator()
                .subscribe({
                    Log.e(TAG,"${it.toString()}")
                    mRootView?.apply {
                        arbitratorMsg(it)
                    }

                }, {
                    Log.e(TAG,"${it.message}")
                    mRootView?.apply {
                        getMsgError(RxUtil.handleResult(it))
                    }

                })
        )
//        getApplyList()
        getMenus()
        getNews(length.toString(),newsCurrent.toString())
        getWechatNews(length.toString(),wechatnewsCurrent.toString())
    }

    /**
     * 获取仲裁员申请情况列表
     */
    override fun getApplyList(){
        addSubscription(
            mainModel.getApplyList("")
                .subscribe({
                    mRootView?.apply {
                        Log.e(TAG, it.toString())
//                        personMater(it)
                        applyList(it)
                    }
                },
                    {
                        mRootView?.apply {
                            Log.e(TAG, it.toString())
                            getApplyError(RxUtil.handleResult(it))
                        }
                    })
        )
    }


    /**
     * 请求菜单
     */
    override fun getMenus() {
        addSubscription(
            mainModel.getMenus("首页")
                .subscribe({
                    Log.e(TAG,"获取首页menus 成功 ${it.toString()}")
                    for (menu in it.iterator()) {
                        if ("APPLY_ARBITRATOR".equals(menu.appMenusType?.apply {} ?: "")) {
                            var applyItem = ApplyItemBean()
                            applyItem.copy(menu)
                            it.remove(menu)
                            mRootView?.apply {applyMenu(applyItem)  }
                            break
                        }

                    }
                    mRootView?.apply {
                        menus(it)
                    }

                },{
                    Log.e(TAG,"获取首页menus 失败 ${it.toString()}")
                    mRootView?.apply {
                        getMenuError(RxUtil.handleResult(it))
                    }
                })
        )
    }

    /**
     * 请求新闻
     */
    override fun getNews(length: String, current: String) {
        addSubscription(
            mainModel.getNews(length,current)
                .subscribe({
                    Log.e(TAG,"getNews 成功 ${it.toString()}")
                    var newsList = mutableListOf<NewsItemBean>()
                    val newsTitle = NewsItemBean(
                        NewsItemBean.TYPE_NEWS_TITLE,
                        NewsTitle("新闻动态")
                    )
                    newsList.add(newsTitle)
                    for (new in it.newsList) {
                        newsList.add(
                            NewsItemBean(
                                NewsItemBean.TYPE_NEWS,
                                new
                            )
                        )
                    }
                    mRootView?.apply {
                        news(newsList)
                    }

                },{
                    Log.e(TAG,"getNews 失败 ${it.toString()}")
                    mRootView?.apply {
                        getNewsError(RxUtil.handleResult(it))
                    }

                })
        )
    }

    /**
     * 请求微信新闻
     */
    override fun getWechatNews(length: String, current: String) {
        addSubscription(
            mainModel.getWechatNews(length,current)
                .subscribe({
                    Log.e(TAG,"getWechatNews 成功 ${it.toString()}")
                    var newsList = mutableListOf<NewsItemBean>()
                    val newsTitle = NewsItemBean(
                        NewsItemBean.TYPE_WECHAT_TITLE,
                        NewsTitle("每日微信")
                    )
                    newsList.add(newsTitle)
                    for (wechatNew in it.newsList) {
                        newsList.add(
                            NewsItemBean(
                                NewsItemBean.TYPE_WECHAT_NEWS,
                                wechatNew
                            )
                        )
                    }
                    mRootView?.apply {
                        wechatNews(newsList)
                    }

                },{
                    Log.e(TAG,"getWechatNews 失败 ${it.toString()}")
                    mRootView?.apply {
                        getWechatNewsError(RxUtil.handleResult(it))
                    }

                })
        )
    }


    /**
     * 界面跳转的方法
     */
    fun jump(context : Context,menu: MenusResponse) {
        var intent:Intent? = null
        when(menu.appMenusType) {

            MenuEnum.CASE_APPLY -> intent = Intent(context, SelectArbitratorTypeActivity::class.java)
            MenuEnum.CASE_DATA -> intent = Intent(context,CaseDataActivity::class.java)
            MenuEnum.CASE_STATISTIC -> intent = Intent(context,CaseStatisticActivity::class.java)
            MenuEnum.ARBFEE_CALCULATE -> intent = Intent(context,CostReckonActivity::class.java)
            MenuEnum.ARB_COMPASS -> {
                intent = Intent(context,ListActivity::class.java)
                intent.putExtra(ListActivity.ACTIVITY_TYPE,ListMessEnum.ARBITRATOR_GUIDE_TYPE)
            }
            MenuEnum.LAW_DOCUMENT -> {
                intent = Intent(context,ListActivity::class.java)
                intent.putExtra(ListActivity.ACTIVITY_TYPE,ListMessEnum.LAW_DOC_TYPE)
            }
            MenuEnum.DOC_TEMPLATE -> {
                intent = Intent(context,ListActivity::class.java)
                intent.putExtra(ListActivity.ACTIVITY_TYPE,ListMessEnum.DOC_TEMPLATE_TYPE)
            }
            MenuEnum.DEALT_WITH -> intent = Intent(context,DealtWithActivity::class.java)
        }
        intent?.apply {
            StartUtil.startActivity(context,this)
        }
    }
}