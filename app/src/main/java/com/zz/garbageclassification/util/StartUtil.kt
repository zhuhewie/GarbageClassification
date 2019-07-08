package com.zz.garbageclassification.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.zz.garbageclassification.view.main.WebActivity
import java.io.File

object StartUtil {
    /**
     * 启动activity的路由
     */
    fun startActivity(context: Context, clazz: Class<*>) {
        val intent =  Intent(context, clazz)
        this.startActivity(context,intent)
    }


    fun startActivity(context: Context,intent: Intent) {
        context.startActivity(intent)
    }

    fun startActivity(context: Context,clazz: Class<*>,dataMap : MutableMap<String,String>) {
        val intent =  Intent(context, clazz)
        for (dataItem in dataMap) {
            intent.putExtra(dataItem.key,dataItem.value)
        }
        StartUtil.startActivity(context,intent)
    }

    /**
     * 启动webActivity
     */
    fun startWebActivity(context: Context,url : String,title : String){
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(WebActivity.WEB_TITLE, title)
        intent.putExtra(WebActivity.WEB_URL, url)
        StartUtil.startActivity(context, intent)
    }

    /**
     * 启动裁剪图片界面
     */
     fun startImageCrop(context: Activity,uri : Uri, outPath : String,resultCode :Int) {
        //构建隐式Intent来启动裁剪程序
        val intent = Intent("com.android.camera.action.CROP")
        //设置数据uri和类型为图片类型
        intent.setDataAndType(uri, "image/*")
        //显示View为可裁剪的
        intent.putExtra("crop", true)
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        //输出图片的宽高均为192
        intent.putExtra("outputX", 192)
        intent.putExtra("outputY", 192)
        //裁剪之后的数据是通过Intent返回 true的话直接返回bitmap，可能会很占内存 不建议
        intent.putExtra("return-data", false)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(outPath)))
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        startActivityForResult(context,intent,resultCode,null)

    }

}