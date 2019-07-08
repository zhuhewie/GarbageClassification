package com.zz.garbageclassification.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatDelegate
import com.zz.garbageclassification.model.http.HttpUrl
import com.facebook.soloader.SoLoader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.zz.garbageclassification.R
import com.zz.garbageclassification.opentools.DynamicTimeFormat
import com.zz.garbageclassification.util.SPUtil
import com.zz.garbageclassification.util.StateUtil


class App constructor() : Application() {
//    public var appComponent : AppComponent? = null
    val TAG = "Arbitrator APP"
    private var refWatcher: RefWatcher? = null

    companion object {
        public lateinit var instance : App


        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as App
            return myApplication.refWatcher
        }

        fun installSmartRefreshLayout() {
            //启用矢量图兼容

            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
            //设置全局默认配置（优先级最低，会被其他设置覆盖）
            SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true)
                layout.setEnableOverScrollDrag(false)
                layout.setEnableOverScrollBounce(true)
                layout.setEnableLoadMoreWhenContentNotFull(true)
                layout.setEnableScrollContentWhenRefreshed(true)
            }
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
                ClassicsHeader(context).setTimeFormat(DynamicTimeFormat("更新于 %s"))
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator{ context, layout ->
                layout.setEnableLoadMoreWhenContentNotFull(true)//内容不满一页时候启用加载更多
                val footer = ClassicsFooter(context)
                footer.setBackgroundResource(android.R.color.white)
                footer.spinnerStyle = SpinnerStyle.Scale//设置为拉伸模式
                //指定为经典Footer，默认是 BallPulseFooter
                footer.setDrawableSize(20f)
                footer
            }
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
//        refWatcher = setUpLeakCanary()
//        appComponent = DaggerAppComponent.builder().build()
        HttpUrl.BASE_URL =  if (StateUtil.isDebug(this)) HttpUrl.TEST_BASE_URL else HttpUrl.RELEASE_BASE_URL
        SoLoader.init(this, false)
        SPUtil.getInstance().init(this)
        installSmartRefreshLayout()


    }

    private fun setUpLeakCanary() : RefWatcher{
        if (LeakCanary.isInAnalyzerProcess(this)){
            return RefWatcher.DISABLED
        }
        return LeakCanary.install(this)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }



}