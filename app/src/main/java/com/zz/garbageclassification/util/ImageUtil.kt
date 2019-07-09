package com.zz.garbageclassification.util

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import com.zz.garbageclassification.app.App
import com.zz.garbageclassification.model.http.HttpUrl
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/4/18 <p>
 * <p> 更改时间 : 2019/4/18 <p>
 * <p> 版本号 : 1 <p>
 *
 */
object ImageUtil {

    /**
     * 更新图片
     */
    fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        App.instance.sendBroadcast(mediaScanIntent)
    }

    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        stream.flush()
        stream.close()
    }

    fun saveBitmap(bitmap: Bitmap) :Uri?{
        try {
            val tmpDir = File("${FileUtil.getCropPath()}/temp")
            if (!tmpDir.exists()) {
                tmpDir.mkdirs()
            }
            //新建文件存储裁剪后的图片
            val img = File("${tmpDir.absolutePath}/${System.currentTimeMillis()}.jpg")
            //打开文件输出流
            val fos = FileOutputStream(img)
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bitmap.compress(Bitmap.CompressFormat.PNG,85,fos)
            //刷新输出流
            fos.flush()
            //关闭输出流
            fos.close()
            return Uri.fromFile(img)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return null
        }
    }

    /**
     * 用于要添加token  的图片加载的url
     * @param imgUrl
     * @return
     */
    fun getTokenImgUrl(imgUrl: String): GlideUrl {

        return GlideUrl(
            imgUrl, LazyHeaders.Builder()
//                .addHeader("Authorization", Token.instance.getBeareeToken())
                .build()
        )
    }


    /**
     * 截取img显示用的url
     */
    fun getImageUrl(imgUrl : String):String {
        return (HttpUrl.BASE_URL +"manageFile/readfiles" + imgUrl)
    }

    /**
     * 处理显示图片的url
     */
    fun getImageUrl2(imgUrl : String):String {
        return (HttpUrl.BASE_URL+imgUrl).replace("/arbitrator//arbitrator/","/arbitrator/")
    }

}