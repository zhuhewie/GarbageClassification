package com.zz.garbageclassification.view.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.app.App
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.http.HttpUrl
import com.zz.garbageclassification.opentools.image.YJImage
import com.zz.garbageclassification.util.AppUtils

/**
 * <p> 文件描述 : 首页菜单的 adapter <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/18 <p>
 * <p> 更改时间 : 2019/3/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class MenuAdapter  : RecyclerView.Adapter<MenuAdapter.ViewHolder>()  {
    var menusList : MutableList<MenusResponse> = mutableListOf()
    private  var clickListenter : ClickListenter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_menu,parent,false)
        val params =itemView.layoutParams as RecyclerView.LayoutParams
        params.height = (AppUtils.getScreenWidth(App.instance) -
                ((params.leftMargin + params.rightMargin + itemView.paddingLeft+itemView.paddingRight) * 4 )) / 4
        itemView.layoutParams = params
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Math.min(menusList.size,8)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val item = menusList[position]
        item.icon = item.icon!!.replaceFirst("/arbitrator/","")
        YJImage.with(holder.imgFunc)
            .load(HttpUrl.BASE_URL + item.icon)
//            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.imgFunc)
        holder.tvFuncName.text = "${item.title}"
        holder.itemView.setOnClickListener {
                this.clickListenter?.setOnClick(holder.itemView,position,item)
        }

//        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
//        params.height = params.width
//
//        holder.itemView.layoutParams = params
    }

    fun setData(menusList : List<MenusResponse>) {
        this.menusList.clear()
        this.menusList.addAll(menusList)
        notifyDataSetChanged()
    }

    fun setOnItemClick(click : ClickListenter) {
        this.clickListenter = click

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFunc : ImageView = itemView.findViewById(R.id.imgFunctionIc)
        val tvFuncName : TextView = itemView.findViewById(R.id.tvFunctionName)
    }
    interface ClickListenter {
        fun setOnClick(view : View,position : Int,menu : MenusResponse)
    }

}