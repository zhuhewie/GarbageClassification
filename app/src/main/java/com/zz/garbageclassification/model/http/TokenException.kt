package com.zz.garbageclassification.model.http

class TokenException(msg : String) :Exception(msg) {
    private var code : Int = 0

    constructor(msg: String,code : Int) : this(msg)

}