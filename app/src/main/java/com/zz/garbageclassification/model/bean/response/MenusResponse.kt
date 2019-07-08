package com.zz.garbageclassification.model.bean.response

import com.zz.garbageclassification.model.bean.main.MainItem

/**
 * <p> 文件描述 : 首页功能菜单信息实体<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/13 <p>
 * <p> 更改时间 : 2019/3/13 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class MenusResponse :MainItem{
    var url:String? = ""
    var sort:String? = ""
    var status:String? = ""
    var title:String? = ""
    var groupName:String? = ""
    var appCode:String? = ""
    var appMenusType:String? = ""
    var icon:String? = ""
    var cid:String? = ""
}