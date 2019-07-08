package com.zz.garbageclassification.base

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.zz.garbageclassification.R
import com.zz.garbageclassification.app.App

abstract class BaseActivity : RootActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(getLayout())
//        hindActionBar()
    }



    /**
     * 隐藏标题栏
     *
     */
    fun hindActionBar() {
        //悬浮状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = resources.getColor(R.color.color_20_000000) //将状态栏设置成透明色
        }
    }


    protected open fun onViewCreated(){}

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId) {
                android.R.id.home  -> {
                    finish()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
//        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
//        MobclickAgent.onPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.getRefWatcher(this)?.watch(this)

    }




}
