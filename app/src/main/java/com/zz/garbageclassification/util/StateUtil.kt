package com.zz.garbageclassification.util

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * 状态判断工具类
 */
class StateUtil {
    companion object {
        /**
         * 判断 当前是 debug 还是release
         */
        fun isDebug(context: Context) : Boolean {
            return try {
                val info = context.applicationInfo
                (info.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}