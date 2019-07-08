package com.zz.garbageclassification.model.bean.response

import com.google.gson.annotations.SerializedName

data class CommonFileResponse(
    val expertIn: List<ExpertIn>,
    val mamageContact: String
)

data class ExpertIn(
    @SerializedName("field")
    val fields: String ,
    val arbitratorType: String,
    val blockDesc: String,
    val isDefault: Boolean,
    override var cid: String,
    val default: Any,
    val embedFields: List<EmbedField>,
    val excelUsing: Boolean,
    val limit: Int,
    override var name: String,
    val sort: Int,
    val subheading: String,
    val title: String,
    val useFor: String
):ExperInBase

data class EmbedField(
    @SerializedName("field")
    val fields: String,
    val canBeChange: Boolean,
    override var cid: String,
    val defValue: Any,
    val descrition: Any,
    val embedFields: List<EmbedField>,
    val fieldSort: Int,
    val fieldType: Any,
    val limit: Int,
    override var name: String,
    val needAudit: Boolean,
    val require: Any,
    val title: String
):ExperInBase

open interface ExperInBase{
    var cid:String
    var name:String

}