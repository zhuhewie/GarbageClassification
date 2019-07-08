package com.zz.garbageclassification.model.bean.response

import com.google.gson.annotations.SerializedName

/**
 * <p> 文件描述 : 请求案件列表的 返回体<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/28 <p>
 * <p> 更改时间 : 2019/3/28 <p>
 * <p> 版本号 : 1 <p>
 *
 */


/**
 * {
"recordsFiltered":250,
"totalPage":32,
"recordsTotal":250,
"content":[
{
"courtType":"合议庭",
"displayNo":"（2018）穗仲案字第 5517 号",
"address":"广州市沿江中路298号江湾大酒店A座8楼",
"nature":"承揽合同纠纷",
"comfirmDatetime":1542211200000,
"arbComposedDate":1542211200000,
"appellees":"福州汇盛建设工程有限公司",
"caseYear":"2018",
"cheifArbitratorName":"叶路",
"caseNo":"5517",
"courtOpenDate":1542384000000,
"accusers":"广州新静界消音材料有限公司",
"name":"第一仲裁庭",
"appelleeArbitratorName":"蔡蔡",
"startTime":1542430800000,
"endTime":1542440700000,
"category":"总会",
"accuserArbitratorName":"邹艳珏",
"status":"已开庭",
"cid":"s1oCVFj0YzLy"
}],
"status":"success"
}
 */
data class CaseResponse(
    @SerializedName("content")
    val caseList: List<Case>,
    val recordsFiltered: Int,
    val recordsTotal: Int,
    val status: String,
    val totalPage: Int
)


/**
 * {
"courtType":"合议庭",
"displayNo":"（2017）穗仲案字第 15564 号",
"address":"广州市沿江中路298号江湾大酒店A座8楼",
"nature":"民间借贷纠纷",
"comfirmDatetime":1521648000000,
"arbComposedDate":1521648000000,
"appellees":"何胄华,李月华",
"caseYear":"2017",
"cheifArbitratorName":"邹艳珏",
"caseNo":"15564",
"courtOpenDate":1513267200000,
"accusers":"梁元亮",
"name":"第五仲裁庭",
"appelleeArbitratorName":"叶路",
"startTime":1513332000000,
"endTime":1513333800000,
"category":"总会",
"accuserArbitratorName":"钟晓东",
"status":"已开庭",
"cid":"0FHEsAt0Mw52"
}
 */
data class Case(
    val accuserArbitratorName: String,
    val accusers: String,
    val address: String,
    val appelleeArbitratorName: String,
    val appellees: String,
    val arbComposedDate: Long,
    val caseNo: String,
    val caseYear: String,
    val category: String,
    val cheifArbitratorName: String,
    val cid: String,
    val comfirmDatetime: Long,
    val courtOpenDate: Long,
    val courtType: String,
    val displayNo: String,
    val endTime: Long,
    val name: String,
    val nature: String,
    val startTime: Long,
    val status: String
)