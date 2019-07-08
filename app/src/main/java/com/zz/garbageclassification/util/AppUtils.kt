package com.zz.garbageclassification.util

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.view.View
import com.zz.garbageclassification.Constants
import com.zz.garbageclassification.app.App
import java.security.MessageDigest

/**
 * Created by xuhao on 2017/12/6.
 * desc: APP 相关的工具类
 */

class AppUtils private constructor() {


    init {
        throw Error("Do not need instantiate!")
    }

    companion object {

        private val DEBUG = true
        private val TAG = "AppUtils"


        /**
         * 得到软件版本号
         *
         * @param context 上下文
         * @return 当前版本Code
         */
        @JvmStatic
        fun getVerCode(context: Context): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                verCode = context.packageManager
                        .getPackageInfo(packageName, 0).versionCode
//                BuildConfig.VERSION_CODE
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }


        /**\
         * 获取账号密码
         */
        @JvmStatic
        fun getUserIdPwd():List<String>{
            val constantString = SPUtil.getInstance().getString(Constants.USER)
            if (!TextUtils.isEmpty(constantString)) {
                val user = ConcealUtil.decrypt(App.instance, Constants.CONCEAL_USER_KEY, constantString)
                if (!TextUtils.isEmpty(user) && user.contains(Constants.SEPARATOR)) {
                    val list = user.split(Constants.SEPARATOR)
                    if (list.size == 2) {
                        return list
                    }
                }
            }
            return emptyList()

        }

        @SuppressLint("MissingPermission")
        @JvmStatic
        fun getPhoneNum(): String?{
            val tm = App.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.line1Number
        }

        /**
         * 获取应用运行的最大内存
         *
         * @return 最大内存
         */
        @JvmStatic
        val maxMemory: Long
            get() = Runtime.getRuntime().maxMemory() / 1024


        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        @JvmStatic
        fun getVerName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager
                        .getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verName
        }


        @SuppressLint("PackageManagerGetSignatures")
                /**
         * 获取应用签名
         *
         * @param context 上下文
         * @param pkgName 包名
         * @return 返回应用的签名
         */
        @JvmStatic
        fun getSign(context: Context, pkgName: String): String? {
            return try {
                @SuppressLint("PackageManagerGetSignatures") val pis = context.packageManager
                        .getPackageInfo(pkgName,
                                PackageManager.GET_SIGNATURES)
                hexDigest(pis.signatures[0].toByteArray())
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                null
            }

        }

        /**
         * 将签名字符串转换成需要的32位签名
         *
         * @param paramArrayOfByte 签名byte数组
         * @return 32位签名字符串
         */
        @JvmStatic
        private fun hexDigest(paramArrayOfByte: ByteArray): String {
            val hexDigits = charArrayOf(48.toChar(), 49.toChar(), 50.toChar(), 51.toChar(), 52.toChar(), 53.toChar(), 54.toChar(), 55.toChar(), 56.toChar(), 57.toChar(), 97.toChar(), 98.toChar(), 99.toChar(), 100.toChar(), 101.toChar(), 102.toChar())
            try {
                val localMessageDigest = MessageDigest.getInstance("MD5")
                localMessageDigest.update(paramArrayOfByte)
                val arrayOfByte = localMessageDigest.digest()
                val arrayOfChar = CharArray(32)
                var i = 0
                var j = 0
                while (true) {
                    if (i >= 16) {
                        return String(arrayOfChar)
                    }
                    val k = arrayOfByte[i].toInt()
                    arrayOfChar[j] = hexDigits[0xF and k.ushr(4)]
                    arrayOfChar[++j] = hexDigits[k and 0xF]
                    i++
                    j++
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }


        /**
         * 获取设备的可用内存大小
         *
         * @param context 应用上下文对象context
         * @return 当前内存大小
         */
        @JvmStatic
        fun getDeviceUsableMemory(context: Context): Int {
            val am = context.getSystemService(
                    Context.ACTIVITY_SERVICE) as ActivityManager
            val mi = ActivityManager.MemoryInfo()
            am.getMemoryInfo(mi)
            // 返回当前系统的可用内存
            return (mi.availMem / (1024 * 1024)).toInt()
        }


        @JvmStatic
        fun getMobileModel(): String {
            var model: String? = Build.MODEL
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }


        /**
         * 获取屏幕宽
         */
        @JvmStatic
        fun getScreenWidth(aty: Context ) : Int{
            val dm = aty.getResources().getDisplayMetrics()
            return  dm.widthPixels
        }


        /**
         * 获取屏幕高度
         *
         */
        @JvmStatic
        fun getScreenHeight(aty: Context ) : Int{
            val dm = aty.getResources().getDisplayMetrics()
            return dm.heightPixels
        }

        /**
         * 获取状态拦高度
         */
        @JvmStatic
        fun getStatusBarHeight(aty: Context ) :Int{
            val resourceId = aty.getResources().getIdentifier("status_bar_height","dimen","android")
            if (resourceId > 0) {
                return aty.getResources().getDimensionPixelSize(resourceId)
            } else {
                return 50
            }
        }

        /**
         * 获取view到屏幕底部的距离
         */
        fun getPopHeight(view : View): Int {
            var location = IntArray(2)
            view.getLocationOnScreen(location)
            return AppUtils.getScreenHeight(App.instance) - location[1] - view.height
        }


        /**
         * 判断是否包含 某包名的应用
         */
        public fun isInstalledApk(packageName : String) : Boolean{
            val manager = App.instance.packageManager
            var installPackages = manager.getInstalledPackages(0)
            installPackages?.apply {
                for (info in this){
                    if (info.packageName.equals(packageName,true)){
                        return true
                    }
                }
            }
            return false
        }

        /**
         * 获取手机系统SDK版本
         *
         * @return 如API 17 则返回 17
         */
        @JvmStatic
        val sdkVersion: Int
            get() = android.os.Build.VERSION.SDK_INT
    }


}