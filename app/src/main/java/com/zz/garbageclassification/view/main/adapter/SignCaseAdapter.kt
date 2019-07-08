package com.zz.garbageclassification.view.main.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.model.bean.eume.CaseDocTypeEnum
import com.zz.garbageclassification.model.bean.response.SignCase
import com.zz.garbageclassification.util.StringUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 * <p> 文件描述 : 通用列表界面的adapter<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/18 <p>
 * <p> 更改时间 : 2019/3/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class SignCaseAdapter : RecyclerView.Adapter<SignCaseAdapter.ItemHolder>(){
    private var signCaseList : MutableList<SignCase> = mutableListOf()
    //用于解析服务器数据
    val dateFormate = SimpleDateFormat("yyyy年MM月dd日")
    private  var clickListenter : ClickListenter? = null
    override fun getItemCount(): Int {
        return signCaseList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = signCaseList[position]
        holder.tvCaseType.text = item.docType!!
        holder.tvCaseType.setTextColor(getCaseTextColor(holder.itemView.context,item.docType!!))
        val showData = dateFormate.format(Date(item?.requestDate?.toLong() ?: 0L) )

        holder.tvData.text = "$showData"
        holder.tvCaseNumber.text = item.displayNo!!
        if (StringUtil.isEmpty(item?.signDate?:null)) {
            holder.tvSignData.visibility = View.GONE
        } else {
            holder.tvSignData.visibility = View.VISIBLE
            val showSignData = dateFormate.format(Date(item?.signDate?.toLong() ?: 0L) )
            holder.tvSignData.text = "签名日期: $showSignData"
        }
        holder.itemView.setOnClickListener {
            clickListenter?.onItemClick(position,holder.itemView,item)
        }
    }

    /**
     * 根据docType 判断文字显示的颜色
     */
    fun getCaseTextColor(context : Context ,docType : String) : Int{
        return when (docType) {
            CaseDocTypeEnum.AWARD ->ContextCompat.getColor(context, R.color.color_1490ea)
            CaseDocTypeEnum.MEDIATION -> ContextCompat.getColor(context, R.color.color_48c3df)
            CaseDocTypeEnum.DECISION -> ContextCompat.getColor(context, R.color.color_ffbd44)
            else -> ContextCompat.getColor(context, R.color.colorMess)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sign_case, parent, false)
        return ItemHolder(itemView)

    }

    /**
     * 刷新数据 - 清空数据并添加
     */
    fun setData(newsList: List<SignCase>) {
        this.signCaseList.clear()
        addData(newsList)
    }

    fun getData() : List<SignCase>{
        return signCaseList
    }

    /**
     * 添加数据 - 在原有的基础上添加数据
     */
    fun addData(caseList: List<SignCase>) {
        this.signCaseList.addAll(caseList)
        notifyDataSetChanged()
    }


    fun setOnItemClickListenter(clickListenter : ClickListenter) {
        this.clickListenter = clickListenter
    }
    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvCaseType : TextView = itemView.findViewById(R.id.tvCaseType)
        val tvData : TextView = itemView.findViewById(R.id.tvData)
        val tvCaseNumber : TextView = itemView.findViewById(R.id.tvCaseNumber)
        val tvSignData : TextView = itemView.findViewById(R.id.tvSignData)
        init {

        }
    }

    interface ClickListenter {
        fun onItemClick(position:Int,view : View,signCase : SignCase)
    }

}