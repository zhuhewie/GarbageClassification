package com.zz.garbageclassification.util

import java.math.BigDecimal

/**
 * 精准的加减运算
 */
object CalculateUtils {
    /**
     * 加法
     */
    fun add(d1 : Double,d2 :Double):Double {
        return BigDecimal(d1).add(BigDecimal(d2))
            .setScale(2,BigDecimal.ROUND_HALF_UP)
            .toDouble()
    }
    /**
     * 减法
     */
    fun sub(d1 : Double,d2 :Double):Double {
        return BigDecimal(d1).subtract(BigDecimal(d2))
            .setScale(2,BigDecimal.ROUND_HALF_UP)
            .toDouble()
    }
    /**
     * 乘法
     */
    fun mul(d1 : Double,d2 :Double):Double {
        return BigDecimal(d1).multiply(BigDecimal(d2))
            .setScale(2,BigDecimal.ROUND_HALF_UP)
            .toDouble()
    }
    /**
     * 除法
     */
    fun div(d1 : Double,d2 :Double):Double {
        return BigDecimal(d1).divide(BigDecimal(d2),3)
            .setScale(2,BigDecimal.ROUND_HALF_UP)
            .toDouble()
    }
}