package com.zz.garbageclassification.model.bean.response

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 *     首页获取仲裁员信息的实体类
 *
 */

class ArbitratorResponse() : ErrorResponse() {

    var isSubmitedMaterial      : Boolean = false   //是否已提交保存过个人基本信息，boolean
    var isGzacArbitrator        : Boolean = false   //是否是广仲仲裁员
    var isOrganArbitrator       : Boolean = false   //是否是机构仲裁员
    var isSubmitedToCommission  : Boolean = false   //是否提交过仲裁员申请（包
    var isTempArbitrator        : Boolean = false   //是否是临时仲裁员
    var imUserParam             : ImgUserParam? = null


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}