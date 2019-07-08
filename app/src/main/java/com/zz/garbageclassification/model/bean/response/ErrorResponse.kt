package com.zz.garbageclassification.model.bean.response

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 *
 */
open class ErrorResponse {
    var error: String? = ""
    var message: String? = ""
    var path: String? = ""
    var status: String? = ""
    var timestamp: String? = ""
}