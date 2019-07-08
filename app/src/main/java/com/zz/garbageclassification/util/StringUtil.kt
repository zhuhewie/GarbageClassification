package com.zz.garbageclassification.util

import android.text.TextUtils
import java.util.regex.Pattern

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


}
