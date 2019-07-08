package com.zz.garbageclassification.view.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.webkit.*
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.BaseActivity
import com.zz.garbageclassification.model.bean.Token
import com.zz.garbageclassification.model.bean.main.WebFunBean
import com.zz.garbageclassification.model.http.HttpUrl
import com.zz.garbageclassification.util.FileUtil
import com.zz.garbageclassification.util.StartUtil
import com.zz.garbageclassification.view.custom.topsnackbar.YJSnackbar
import com.github.mikephil.charting.utils.FileUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.activity_web.view.*
import java.io.File
import java.io.FileOutputStream

private val TAG = "WebActivity"
class WebActivity : BaseActivity() {
    companion object {
        val WEB_TITLE = "webTitle"
        val WEB_URL = "webUrl"
        val WEB_FUN1 = "webFun1"
        val WEB_FUN2 = "webFun2"
    }
    var tilteName = "网页"
    var url = "file:///android_asset/web/service_agreement.html"
    var fun1Data :WebFunBean?=null
    var fun2Data :WebFunBean?=null
    var token = mutableMapOf<String ,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        intent?.extras?.apply {
            val webTitle : String = getString(WEB_TITLE,tilteName)
            tilteName = webTitle?.apply { }?:tilteName
            url = getString(WEB_URL,url)?.apply {}?:url
            fun1Data = getParcelable<WebFunBean>(WEB_FUN1)?.apply {
                tvFun1.visibility = View.VISIBLE
                tvFun1.text = name
            }
            fun2Data = getParcelable<WebFunBean>(WEB_FUN2)?.apply {
                tvFun2.visibility = View.VISIBLE
                tvFun2.text = name
            }


            Log.w("webview title:",tilteName)
            Log.w("webview url:",url)

        } ?: finish()

        llBackView.setOnClickListener {
            finish()
        }
        tvFun1.setOnClickListener {
            funOne()
        }

        tvFun2.setOnClickListener {
            funTwo()

        }
        titleName.text = tilteName
        webView.isDrawingCacheEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.setUseWideViewPort(true)
        webView.settings.setSupportZoom(true)
        webView.settings.setBuiltInZoomControls(true)
        webView.settings.setDisplayZoomControls(false)


        CookieSyncManager.createInstance(this@WebActivity)
        CookieSyncManager.getInstance().startSync()
        CookieManager.getInstance()
            .setAcceptCookie(true)
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP) {
        CookieManager.getInstance()
            .setAcceptThirdPartyCookies(webView,true)
        }

        token["Authorization"] = Token.instance.getBeareeToken()
        webView.settings.setAppCacheEnabled(true)


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view!!.loadUrl(request!!.url.toString())
                return true
            }
        }

    }

    fun funOne(){
        fun1Data?.apply {
            when(funType) {
                1 -> {
                    val intent = Intent(this@WebActivity, WebActivity::class.java)
                    intent.putExtra(WebActivity.WEB_TITLE,"证书详情")
                    intent.putExtra(WebActivity.WEB_URL, HttpUrl.ECERTIFI_DETAIL)
                    StartUtil.startActivity(this@WebActivity,intent)
                }
            }
        }
    }
    fun funTwo(){
        fun2Data?.apply {
            when(funType) {
                2 -> {
                    try {//截图
                        Log.d(TAG,"创建线程 ${Thread.currentThread().name}")
                        Observable
                            .create<Int> {
                                Log.d(TAG,"发射线程 ${Thread.currentThread().name}")
                                val bitmap = webView.drawingCache
                                val fls =
                                    FileOutputStream(
                                        File(
                                            FileUtil.getEcertificate(),
                                            "${System.currentTimeMillis()}.png"
                                        )
                                    )
                                val result = bitmap.compress(Bitmap.CompressFormat.PNG, 70, fls)
                                if (!result) {
                                    it.onError(Exception("保存图片失败"))
                                } else {
                                    it.onNext(1)
                                }
                                fls.flush()
                                fls.close()
                                bitmap.recycle()

                            }
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                Log.d(TAG, "接受线程 ${Thread.currentThread().name}")
                                YJSnackbar.make(tvFun2, "图片下载成功", YJSnackbar.LENGTH_SHORT).show()
                            }, {
                                Log.d(TAG, "接受线程 ${Thread.currentThread().name}")
                                YJSnackbar.make(tvFun2, "下载失败", YJSnackbar.LENGTH_SHORT).show()
                            })
                        //后续可能需要添加压缩方法
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }



    override fun onResume() {
        super.onResume()
        webView.onResume()
        webView.loadUrl(url,token)
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }
}
