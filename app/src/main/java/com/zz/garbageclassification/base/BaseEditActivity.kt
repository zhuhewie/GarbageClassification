package com.zz.garbageclassification.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 * 界面中有edittext的activity
 * 主要用于键盘的隐藏
 */
abstract class BaseEditActivity:BaseActivity() {
    lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        } finally {

        }

    }

    /**
     * 点击的时候判断,是否有软键盘,有了,就关闭
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.getAction() == MotionEvent.ACTION_DOWN) {
            imm?.apply {
                if (imm != null && imm.isActive()) {
                    if (isShouldHideInput(getCurrentFocus(),ev)) {
                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return if (event.x > left && event.x < right
                && event.y > top && event.y < bottom
            ) {
                // 点击的是输入框区域，保留点击EditText的事件
                false
            } else {
                true
            }
        }
        return false
    }

    /**
     * 根据坐标获取坐标所在的view
     */
    fun getViewByXY(x : Int,y: Int){
        var view = getWindow().getDecorView()
        findViewByXY(view,x,y)
    }

    private fun findViewByXY(view: View?,x : Int,y: Int) :View?{
        var chileView: View? = null

        if (view == null) return chileView
        if (view is ViewGroup) {
            for (index in 0 until view.getChildCount()) {
                chileView = findViewByXY(view.getChildAt(index),x,y)
                if (this != null) {
                    break
                }
            }

        } else {
            chileView =getTouchTarget(view,x,y)
        }
        return chileView
    }

    private fun  getTouchTarget(view :View ,  x :Int, y :Int):View? {
        var targetView :View?= null
        // 判断view是否可以聚焦
         var touchableViews:ArrayList<View> = view.getTouchables();
        for ( child in touchableViews) {
            if (isTouchPointInView(child, x, y)) {
                targetView = child
                break
            }
        }
        return targetView
    }

    private fun isTouchPointInView(view :View ,  x :Int, y :Int) :Boolean{
//        var location = arrayOfNulls<Int>(2)
        var location = intArrayOf(0,0)
        view.getLocationOnScreen(location)
        var left:Int = location[0]
        var top :Int = location[1]
        var right :Int= left + view.getMeasuredWidth()
        var bottom :Int= top + view.getMeasuredHeight()
        if (view.isClickable() && y >= top && y <= bottom && x >= left
            && x <= right) {
            return true
        }
        return false
    }
}