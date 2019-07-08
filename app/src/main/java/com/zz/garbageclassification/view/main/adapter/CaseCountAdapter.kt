package com.zz.garbageclassification.view.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.model.bean.response.CaseCountResponse
import com.zz.garbageclassification.model.bean.response.News
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.view.login.AuthCodeActivity
import com.zz.garbageclassification.view.main.WebActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * <p> 文件描述 : 通用列表界面的adapter<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/18 <p>
 * <p> 更改时间 : 2019/3/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class CaseCountAdapter : RecyclerView.Adapter<CaseCountAdapter.ItemHolder>(){
    private var caseCount : CaseCountResponse? = null
    val size = 12

    private  var clickListenter : ClickListenter? = null
    override fun getItemCount(): Int {
        return size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val netCount = if (position < size) {caseCount?.WEBCASE?.get(position) ?: 0} else {0}
        val noNetCount = if (position < size) {caseCount?.NONWEBCASE?.get(position) ?: 0} else{0}
        holder.tvMou.text = "${position+1} 月"
        holder.tvNum.text = "${netCount + noNetCount}"
        holder.tvNetCaseCount.text = "${netCount}"
        holder.tvNotNetCaseCount.text = "${noNetCount}"

        holder.itemView.setOnClickListener {
            clickListenter?.onItemClick(position,holder.itemView,netCount,noNetCount)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_case_count, parent, false)
        return ItemHolder(itemView)

    }


    fun getData() : CaseCountResponse?{
        return this.caseCount
    }

    /**
     * 添加数据 -
     */
    fun addData(caseCount: CaseCountResponse?) {
        this.caseCount = caseCount
        notifyDataSetChanged()
    }


    fun setOnItemClickListenter(clickListenter : ClickListenter) {
        this.clickListenter = clickListenter
    }
    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvNum : TextView = itemView.findViewById(R.id.tvNum)
        val tvMou : TextView = itemView.findViewById(R.id.tvMou)
        val tvNetCaseCount : TextView = itemView.findViewById(R.id.tvNetCaseCount)
        val tvNotNetCaseCount : TextView = itemView.findViewById(R.id.tvNotNetCaseCount)
    }

    interface ClickListenter {
        fun onItemClick(position:Int,view : View,netCount : Int,noNetCount : Int)
    }

}