package com.zz.garbageclassification.view.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.model.bean.main.NewsItemBean
import com.zz.garbageclassification.model.bean.eume.ListMessEnum
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.view.main.WebActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * <p> 文件描述 : 首页新闻和每日微信新闻的adapter <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/18 <p>
 * <p> 更改时间 : 2019/3/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    private var newsListBean : MutableList<NewsItemBean> = mutableListOf()
    //用于解析服务器数据
    val dateFormate = SimpleDateFormat("yyyy-MM-dd")

    fun setNews(newsListBean: List<NewsItemBean>) {
        for (item in this.newsListBean.iterator()) {
            if (item.newType == NewsItemBean.TYPE_NEWS || item.newType == NewsItemBean.TYPE_NEWS_TITLE) {
                this.newsListBean.remove(item)
            }
        }
        this.newsListBean.addAll(newsListBean)
        notifyDataSetChanged()
    }
    fun setWechatNews(newsListBean: List<NewsItemBean>) {
        for (item in this.newsListBean.iterator()) {
            if (item.newType == NewsItemBean.TYPE_WECHAT_NEWS || item.newType == NewsItemBean.TYPE_WECHAT_TITLE) {
                this.newsListBean.remove(item)
            }
        }
        this.newsListBean.addAll(newsListBean)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            NewsItemBean.TYPE_NEWS_TITLE, NewsItemBean.TYPE_WECHAT_TITLE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_new_title,parent,false)
                TitleHolder(itemView)

            }
            NewsItemBean.TYPE_NEWS , NewsItemBean.TYPE_WECHAT_NEWS -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_new_content,parent,false)
                ContextHolder(itemView)
            }
            else -> TitleHolder(View(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return newsListBean.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = newsListBean[position]
        val type = when(item.newType) {
            NewsItemBean.TYPE_NEWS_TITLE -> ListMessEnum.NEWS_INFO_TYPE
            NewsItemBean.TYPE_WECHAT_TITLE -> ListMessEnum.WECHAT_INFO_TYPE
            else -> -1
        }
        if (holder is TitleHolder) {
            holder.tvTitle.text = item.newsTitle?.title ?: "新闻"
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context,ListActivity::class.java)
                intent.putExtra(ListActivity.ACTIVITY_TYPE,type)
                StartUtil.startActivity(holder.itemView.context,intent)
            }
        } else if (holder is ContextHolder) {
            holder.tvMess.text = item.news?.title ?:"新闻"
            val showData = dateFormate.format(Date(item.news?.createTime?.toLong() ?: 0L) )
            holder.tvData.text = showData

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context,WebActivity::class.java)
                intent.putExtra(WebActivity.WEB_URL,item.news?.newsUrl?:"")
                intent.putExtra(WebActivity.WEB_TITLE,type)
                StartUtil.startActivity(holder.itemView.context,intent)

            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return newsListBean[position].newType
    }


    class TitleHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tvNewsTitle)
        init {

        }
    }

    class ContextHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvMess: TextView = itemView.findViewById(R.id.tnNewsMess)
        val tvData : TextView = itemView.findViewById(R.id.tnNewsData)

    }

//    interface ClickListenter{
//        fun onItemClick()
//    }
}