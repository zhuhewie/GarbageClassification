package com.zz.garbageclassification.model.bean.response

/**
 * 重置密码 - 请求接口
 */
data class ResetAuthCodeResponse(
  var msg : String = "",
  var guid : String = ""
) {
}