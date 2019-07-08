package com.zz.garbageclassification.view.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.app.App
import com.zz.garbageclassification.model.bean.eume.ListMessEnum
import com.zz.garbageclassification.model.bean.eume.MainItemEnum
import com.zz.garbageclassification.model.bean.main.ApplyItemBean
import com.zz.garbageclassification.model.bean.main.BannerBean
import com.zz.garbageclassification.model.bean.main.MainItem
import com.zz.garbageclassification.model.bean.main.NewsItemBean
import com.zz.garbageclassification.model.bean.response.MenusResponse
import com.zz.garbageclassification.model.http.HttpUrl
import com.zz.garbageclassification.opentools.image.GlideImageBannerLoader
import com.zz.garbageclassification.opentools.image.YJImage
import com.zz.garbageclassification.util.AppUtils
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.view.main.WebActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * <p> 文件描述 : 首页的 adapter <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/18 <p>
 * <p> 更改时间 : 2019/3/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class MainItemAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var itemList : MutableList<MainItem> = mutableListOf()
    private var clickListenter : ClickListenter? = null
    private var applyClickListenter : ApplyClickListenter? = null
    private var menusList : MutableList<MenusResponse> = mutableListOf()
    private var newsList : MutableList<NewsItemBean> = mutableListOf()
    private var wechatNewsList : MutableList<NewsItemBean> = mutableListOf()
    private var bannerData : BannerBean = BannerBean()
    private var applyData : ApplyItemBean? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            MainItemEnum.APPLY_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_apply_menu,parent,false)
                return ApplyHolder(itemView)
            }
            MainItemEnum.MENU_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_menu,parent,false)
                val params =itemView.layoutParams as RecyclerView.LayoutParams
                params.height = (AppUtils.getScreenWidth(App.instance) -
                        ((params.leftMargin + params.rightMargin + itemView.paddingLeft+itemView.paddingRight) * 4 )) / 4
                itemView.layoutParams = params
                return MenuHolder(itemView)
            }
            MainItemEnum.BANNER_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_banner,parent,false)
                return BannerHolder(itemView)
            }
            MainItemEnum.NEWS_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_new_content,parent,false)
                return NewsHolder(itemView)
            }
            MainItemEnum.NEWS_TITLE_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_new_title,parent,false)
                return NewsTitleHolder(itemView)
            }
            else ->{
                return NewsHolder(View(parent.context))
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        if (holder is ApplyHolder) {
            if (item is ApplyItemBean) {    //申请仲裁员
                if ("APPLY_ARBITRATOR".equals(item.appMenusType?.apply {  }?:"")) {
                    holder.itemView.visibility = View.VISIBLE
                    holder.itemView.setOnClickListener {
                        applyClickListenter?.setOnClick(holder.itemView,position,item)
                    }
                } else {
                    holder.itemView.visibility = View.GONE
                }
            }
        } else if (holder is MenuHolder) { //菜单
            if (item is MenusResponse) {
                item.icon = item.icon!!.replaceFirst("/arbitrator/","")
                YJImage.with(holder.imgFunc)
                    .load(HttpUrl.BASE_URL + item.icon)
                    .into(holder.imgFunc)
                holder.tvFuncName.text = "${item.title}"
                holder.itemView.setOnClickListener {
                    this.clickListenter?.setOnClick(holder.itemView,position,item)
                }
            }
        } else if (holder is BannerHolder) {  //轮播图
            if (item is BannerBean) {
                holder.banner.setImages(item.list)
                    .setIndicatorGravity(BannerConfig.LEFT)
                    .setImageLoader(GlideImageBannerLoader())
                    .start()
            }
        } else if (holder is NewsHolder) {
            if (item is NewsItemBean) {
                holder.tvMess.text = item.news?.title ?:"新闻"
                val showData = holder.dateFormate.format(Date(item.news?.createTime?.toLong() ?: 0L) )
                holder.tvData.text = showData
                val type = when(item.newType) {
                    NewsItemBean.TYPE_NEWS_TITLE -> ListMessEnum.NEWS_INFO_NAME
                    NewsItemBean.TYPE_WECHAT_TITLE -> ListMessEnum.WECHAT_INFO_NAME
                    else -> "新闻"
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, WebActivity::class.java)
                    intent.putExtra(WebActivity.WEB_URL,item.news?.newsUrl?:"")
                    intent.putExtra(WebActivity.WEB_TITLE,item?.news?.title?:type)
                    StartUtil.startActivity(holder.itemView.context,intent)

                }
            }

        } else if (holder is NewsTitleHolder) {
            if (item is NewsItemBean) {
                holder.tvTitle.text = item.newsTitle?.title ?: "新闻"
                val type = when(item.newType) {
                    NewsItemBean.TYPE_NEWS_TITLE -> ListMessEnum.NEWS_INFO_TYPE
                    NewsItemBean.TYPE_WECHAT_TITLE -> ListMessEnum.WECHAT_INFO_TYPE
                    else -> -1
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, ListActivity::class.java)
                    intent.putExtra(ListActivity.ACTIVITY_TYPE,type)
                    StartUtil.startActivity(holder.itemView.context,intent)
                }
            }
        }
    }

    fun setApplyData(applyData:ApplyItemBean) {
        this.applyData = applyData
        refreshData()
    }

    fun setMenuData(menusList : List<MenusResponse>) {
        this.menusList.clear()
        this.menusList.addAll(menusList)
        refreshData()
    }

    fun getMenuData() = menusList


    fun getItemList() = itemList

    fun setBannerData(bannerData: BannerBean){
        this.bannerData = bannerData
        refreshData()
    }

    fun setNews(newsListBean: List<NewsItemBean>) {
//        for (item in this.newsList.iterator()) {
//            if (item.newType == NewsItemBean.TYPE_NEWS || item.newType == NewsItemBean.TYPE_NEWS_TITLE) {
//                this.newsList.remove(item)
//            }
//        }
        newsList.clear()
        this.newsList.addAll(newsListBean)
        refreshData()
    }
    fun setWechatNews(newsListBean: List<NewsItemBean>) {
//        for (item in this.newsList.iterator()) {
//            if (item.newType == NewsItemBean.TYPE_WECHAT_NEWS || item.newType == NewsItemBean.TYPE_WECHAT_TITLE) {
//                this.newsList.remove(item)
//            }
//        }
        this.wechatNewsList.clear()
        this.wechatNewsList.addAll(newsListBean)
        refreshData()
    }

    fun refreshData() {
        itemList.clear()
        applyData?.apply { itemList.add(this) }
        itemList.addAll(menusList)
        itemList.add(bannerData)
        itemList.addAll(newsList)
        itemList.addAll(wechatNewsList)

        notifyDataSetChanged()
    }

    fun setMenuItemClick(click : ClickListenter) {
        this.clickListenter = click
    }
    fun setApplyItemClick(click : ApplyClickListenter) {
        this.applyClickListenter = click
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemList[position]
        return if (item is MenusResponse) {
            MainItemEnum.MENU_TYPE
        } else if (item is BannerBean) {
            MainItemEnum.BANNER_TYPE
        } else if (item is NewsItemBean) {
            when (item.newType){
                NewsItemBean.TYPE_NEWS ,  NewsItemBean.TYPE_WECHAT_NEWS ->{
                    MainItemEnum.NEWS_TYPE
                }
                NewsItemBean.TYPE_NEWS_TITLE ,  NewsItemBean.TYPE_WECHAT_TITLE ->{
                    MainItemEnum.NEWS_TITLE_TYPE
                }
                else ->{
                    MainItemEnum.NEWS_TYPE
                }
            }
        } else if (item is ApplyItemBean && "APPLY_ARBITRATOR".equals(item.appMenusType?.apply {  }?:"")){
            MainItemEnum.APPLY_TYPE
        } else {
            MainItemEnum.NEWS_TYPE
        }

    }


    class ApplyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvApplyTitle : TextView = itemView.findViewById(R.id.tvApplyTitle)
        val tvApplyMes : TextView = itemView.findViewById(R.id.tvApplyMes)
    }
    class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFunc : ImageView = itemView.findViewById(R.id.imgFunctionIc)
        val tvFuncName : TextView = itemView.findViewById(R.id.tvFunctionName)
    }


    class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner : Banner = itemView.findViewById(R.id.banner)
        init {
            val layoutParams = banner.layoutParams
            layoutParams.width = AppUtils.getScreenWidth(App.instance)
            layoutParams.height = (AppUtils.getScreenWidth(App.instance) * 0.2666).toInt()
            banner.layoutParams = layoutParams
        }
    }




    class NewsTitleHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tvNewsTitle)
    }

    class NewsHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvMess: TextView = itemView.findViewById(R.id.tnNewsMess)
        val tvData : TextView = itemView.findViewById(R.id.tnNewsData)
        val dateFormate = SimpleDateFormat("yyyy-MM-dd")


    }
    interface ClickListenter {
        fun setOnClick(view : View,position : Int,menu : MenusResponse)
    }
    interface ApplyClickListenter {
        fun setOnClick(view : View,position : Int,applyData : ApplyItemBean)
    }

}