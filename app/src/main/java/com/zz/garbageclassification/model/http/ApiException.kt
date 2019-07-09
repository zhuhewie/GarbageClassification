package com.zz.garbageclassification.model.http

class ApiException(msg : String) :Exception(msg) {
    var code : Int = 0

    constructor(msg: String,code : Int) : this(msg)
    constructor(code : Int,msg: String) : this(msg)
}