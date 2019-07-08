package com.zz.garbageclassification.view.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zz.garbageclassification.R
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
class ListAdapter : RecyclerView.Adapter<ListAdapter.ItemHolder>(){
    private var messList : MutableList<News> = mutableListOf()

    private  var clickListenter : ClickListenter? = null
    override fun getItemCount(): Int {
        return messList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = messList[position]
        holder.tvContent.text = item.title
        holder.itemView.setOnClickListener {
            clickListenter?.onItemClick(position,holder.itemView,item.title,item.newsUrl)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemHolder(itemView)

    }

    /**
     * 刷新数据 - 清空数据并添加
     */
    fun setData(newsList: List<News>) {
        this.messList.clear()
        addData(newsList)
    }

    fun getData() : List<News>{
        return messList
    }

    /**
     * 添加数据 - 在原有的基础上添加数据
     */
    fun addData(newsList: List<News>) {
        this.messList.addAll(newsList)
        notifyDataSetChanged()
    }


    fun setOnItemClickListenter(clickListenter : ClickListenter) {
        this.clickListenter = clickListenter
    }
    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvContent : TextView = itemView.findViewById(R.id.tnContext)
    }

    interface ClickListenter {
        fun onItemClick(position:Int,view : View,title : String,url : String)
    }

}