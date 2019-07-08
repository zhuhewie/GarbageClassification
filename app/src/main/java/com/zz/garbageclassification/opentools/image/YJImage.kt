package com.zz.garbageclassification.opentools.image

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager


/**
 * 图片加载路由类
 *
 */
class YJImage private constructor(){
    /**
     *
     *        val options = RequestOptions()
    .transforms()
    .placeholder(R.drawable.img_about_us)
    .error(R.drawable.)

    Glide.with(this)
    .asBitmap()  //asGif  /asBitmap  /asDrawale  /asFile
    .load(TestData.user_cover)      //加载的url
    .apply(option)
    .listener()
    //            .preload()
    //            .submit()
    .into(imgUserCover)
     */



    companion object {

        fun with (view : View) : RequestManager{
            return Glide.with(view)
        }

        fun with (activity : FragmentActivity) : RequestManager{
            return Glide.with(activity)
        }
        fun with (fragment : Fragment) : RequestManager{
            return Glide.with(fragment)
        }
        fun with (context: Context) : RequestManager{
            return Glide.with(context)
        }
        fun with (fragment : android.app.Fragment ) : RequestManager{
            return Glide.with(fragment)
        }
        fun with (activity : Activity) : RequestManager{
            return Glide.with(activity)
        }



    }
}