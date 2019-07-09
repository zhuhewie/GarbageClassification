package com.arbitratorapp.view.custom

import android.content.Context
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BaseTransientBottomBar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntDef
import com.zz.garbageclassification.R
import com.zz.garbageclassification.util.AppUtils
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class YJToast private constructor(){
    companion object {
        @IntDef(LENGTH_SHORT, LENGTH_LONG)
        @Retention(RetentionPolicy.SOURCE)
        annotation class Duration

        /**
         * Show the view or text notification for a short period of time.  This time
         * could be user-definable.  This is the default.
         * @see .setDuration
         */
        const val LENGTH_SHORT = 0

        /**
         * Show the view or text notification for a long period of time.  This time
         * could be user-definable.
         * @see .setDuration
         */
        const val LENGTH_LONG = 1
        private var mToast : Toast? = null
        val instance: YJToast by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            YJToast()
        }


        public fun makeText(context: Context, text :CharSequence, @Duration duration:Int):YJToast{
            var view : View? = null
            view = LayoutInflater.from(context).inflate(R.layout.view_toast_layout,null)
            if (mToast != null) {
                mToast?.cancel()
            }
            mToast = Toast(context)
            var textView = view?.findViewById<TextView>(R.id.toast_text)
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
            var params=textView?.getLayoutParams()
            if (params == null) {
                params = ConstraintLayout.LayoutParams(AppUtils.getScreenWidth(context),ViewGroup.LayoutParams.WRAP_CONTENT)
            } else {
                params.width=AppUtils.getScreenWidth(context)
                params.height=ViewGroup.LayoutParams.WRAP_CONTENT
            }
            textView?.setLayoutParams(params)
            textView?.setBackgroundResource(R.color.colorPrimary)
            textView?.setText(text)
            mToast?.apply {
                setDuration(duration)
                setGravity(Gravity.TOP,0,0)
                setView(view)
            }

            return instance
        }
        public fun makeErrorText(context: Context, text :CharSequence, @Duration duration:Int):YJToast{
            var view : View? = null
            view = LayoutInflater.from(context).inflate(R.layout.view_toast_layout,null)
            if (mToast != null) {
                mToast?.cancel()
            }
            mToast = Toast(context)
            var textView = view?.findViewById<TextView>(R.id.toast_text)
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
            var params=textView?.getLayoutParams()
            if (params == null) {
                params = ConstraintLayout.LayoutParams(AppUtils.getScreenWidth(context),ViewGroup.LayoutParams.WRAP_CONTENT)
            } else {
                params.width=AppUtils.getScreenWidth(context)
                params.height=ViewGroup.LayoutParams.WRAP_CONTENT
            }
            textView?.setBackgroundResource(R.color.color_waring)
            textView?.setLayoutParams(params)
            textView?.setText(text)
            mToast?.apply {
                setDuration(duration)
                setGravity(Gravity.TOP,0,0)
                setView(view)
            }

            return instance
        }



    }

    public fun show(){

        mToast?.apply {
            show()
        }
    }

}