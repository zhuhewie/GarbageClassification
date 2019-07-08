package com.zz.garbageclassification.view.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.model.bean.response.News
import com.zz.garbageclassification.model.bean.response.SessionCase
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.util.StringUtil
import com.zz.garbageclassification.view.login.AuthCodeActivity
import com.zz.garbageclassification.view.main.SessionFragment
import com.zz.garbageclassification.view.main.WebActivity
import kotlinx.android.synthetic.main.activity_register.*
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
class SessionAdapter : RecyclerView.Adapter<SessionAdapter.ItemHolder>(){
    private var sessionList : MutableList<SessionCase> = mutableListOf()
    private val SESSION_TYPE = "待开庭"       //待开庭的案件类型
    private val SESSIONED_TYPE = "已开庭"     //已开庭的案件类型
    private var sessionType :String = SESSION_TYPE           //M默认 待开庭的类型

    //用于解析服务器数据
    val dateFormate = SimpleDateFormat("yyyy年MM月dd日 EE hh:mm")
    val dateEndFormate = SimpleDateFormat("hh:mm")
    private  var clickListenter : ClickListenter? = null
    override fun getItemCount(): Int {
        return sessionList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = sessionList[position]
        holder.tvCaseNumber.text = "${item.DISPLAY_NO}"
        val showStartData = dateFormate.format(Date(item.START_TIME?.toLong() ?: 0L) )
        val showEndData = dateEndFormate.format(Date(item.END_TIME?.toLong() ?: 0L) )
        var typeString = if (sessionType.equals(SESSION_TYPE)) "预约" else "开庭"
        holder.tvSessionData.text = "${typeString}时间: $showStartData ~ $showEndData"
        holder.tvSessionAddress.text = "${typeString}地点: ${item.CATEGORY} ${item.NAME}"
//        holder.tvCaseNumber.text = item.title
        holder.itemView.setOnClickListener {
            clickListenter?.onItemClick(position,holder.itemView,item)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return ItemHolder(itemView)

    }

    /**
     * 刷新数据 - 清空数据并添加
     */
    fun setData(newsList: List<SessionCase>,sessionType :String) {
        this.sessionList.clear()
        addData(newsList,sessionType)
    }

    fun getData() : List<SessionCase>{
        return sessionList
    }

    /**
     * 添加数据 - 在原有的基础上添加数据
     */
    fun addData(newsList: List<SessionCase>,sessionType :String) {
        this.sessionType = sessionType
        this.sessionList.addAll(newsList)
        notifyDataSetChanged()
    }


    fun setOnItemClickListenter(clickListenter : ClickListenter) {
        this.clickListenter = clickListenter
    }
    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvCaseNumber : TextView = itemView.findViewById(R.id.tvCaseNumber)
        val tvSessionData : TextView = itemView.findViewById(R.id.tvSessionData)
        val tvSessionAddress : TextView = itemView.findViewById(R.id.tvSessionAddress)
    }

    interface ClickListenter {
        fun onItemClick(position:Int,view : View,itemData :SessionCase)
    }

}