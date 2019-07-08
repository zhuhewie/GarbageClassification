package com.zz.garbageclassification.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * 格式化时间
 */
class DateUtil {

    companion object {

        val dateFormatString1 = "yyyy-MM-dd"
        val dateFormatString2 = "yyyy-MM-dd hh:mm:ss"
        val dateFormatString3 = "yyyy-MM-dd hh:mm"
        val dateFormatYear = "yyyy"



        fun getTimeLong(date: Long): String {//可根据需要自行截取数据显示
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(Date(date?.apply {}?:0L))
        }
        fun getTimeLong(date: Long,rule : String): String {//可根据需要自行截取数据显示
            val format = SimpleDateFormat(rule)
            return format.format(Date(date?.apply {}?:0L))
        }
        fun getTime(date: Date): String {//可根据需要自行截取数据显示
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(date)
        }
        fun getTime(date: Date, rule : String): String {//可根据需要自行截取数据显示
            val format = SimpleDateFormat(rule)
            return format.format(date)
        }

        //日期选择的当前时间
        fun getSelectData():Calendar{
            return Calendar.getInstance()
        }

        fun getStartData():Calendar{
            val startDate = Calendar.getInstance()
            startDate.set(1900,0,1)
            return startDate
        }

        fun getEndData():Calendar{
            val endDate = Calendar.getInstance()
            endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
            return endDate
        }

//        /**
//         * 获取当前日期是星期几<br></br>
//         *
//         * @param date
//         * @return 当前日期是星期几
//         */
//        fun getWeekOfDate(date: Date): String {
//            val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
//            val cal = Calendar.getInstance()
//            cal.time = date
//            var w = cal.get(Calendar.DAY_OF_WEEK) - 1
//            if (w < 0)
//                w = 0
//            return weekDays[w]
//        }

    }

}