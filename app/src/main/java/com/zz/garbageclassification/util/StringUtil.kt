package com.zz.garbageclassification.util

import android.text.TextUtils
import java.util.regex.Pattern
import android.provider.SyncStateContract.Helpers.update
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object StringUtil {


    /**
     * 判断是否是合法电话号码
     */
    fun isPhone(phone : String) : Boolean {
        val pattern = Pattern.compile("""1[0-9]{10}${'$'}""")
        return !TextUtils.isEmpty(phone) && pattern.matcher(phone).matches()
    }

    /**
     * 判断email是否正确
     */
    fun isEmail(email :String ) :Boolean {
        val pattern = Pattern.compile("""^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}${'$'}""")
        return !TextUtils.isEmpty(email) && pattern.matcher(email).matches()
    }

    /**
     * 判断密码时候合法
     */
    fun isPassword(password:String):Boolean {
        val pattern = Pattern.compile("""^(?![a-zA-z]+${'$'})(?!\d+${'$'})(?![!@#${'$'}%^&*]+${'$'})[a-zA-Z\d!@#${'$'}%^&*]{6,}+${'$'}""")
        return !TextUtils.isEmpty(password) && pattern.matcher(password).matches()
    }

    /**
     * 判断字符串是否为空
     */
    fun isEmpty(text : String?) : Boolean {
        return if (!TextUtils.isEmpty(text)) {
            return text.equals("null" ,true)
        } else {
            true
        }
    }
    fun getMD5(string: String): String? {
        try {
            val instance:MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
            val digest:ByteArray = instance.digest(string.toByteArray())//对字符串加密，返回字节数组
            var sb : StringBuffer = StringBuffer()
            for (b in digest) {
                var i :Int = b.toInt() and 0xff//获取低八位有效值
                var hexString = Integer.toHexString(i)//将整数转化为16进制
                if (hexString.length < 2) {
                    hexString = "0" + hexString//如果是一位的话，补0
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""

    }

}
