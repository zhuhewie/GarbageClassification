package com.zz.garbageclassification.view.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.zz.garbageclassification.Constants
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.RootActivity
import com.zz.garbageclassification.model.bean.Token
import com.zz.garbageclassification.model.http.model.LoginModel
import com.zz.garbageclassification.util.AppUtils
import com.zz.garbageclassification.util.ConcealUtil
import com.zz.garbageclassification.util.SPUtil
import com.zz.garbageclassification.view.login.LoginActivity
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit.SECONDS

class SplashActivity : RootActivity(), EasyPermissions.PermissionCallbacks {


    private val TAG = "Arbitrator Splash"

    val loginModel by lazy { LoginModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkPermission()


    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val perms = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (EasyPermissions.hasPermissions(this, *perms)) {
            checkToken()
            startMain()
        } else {

            EasyPermissions.requestPermissions(this, "仲裁人应用需要以下权限，请允许", 0, *perms)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (
                    perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && perms.contains(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && perms.contains(Manifest.permission.INTERNET)
                ) {
                }

                checkToken()
                startMain()
            }
        }
    }

    fun startMain() {
        val disposable = Observable.timer(3, SECONDS)
            .subscribe(Consumer {
                var intent: Intent?
                if (Token.instance.isLogin()) {
                    intent = Intent(this, Main2Activity::class.java)
                } else {
                    intent = Intent(this, LoginActivity::class.java)

                }
                startActivity(intent)
                finish()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 自动登录
     */
    @SuppressLint("CheckResult")
    fun autoLogin() {
        val list = AppUtils.getUserIdPwd()
        if (list.isEmpty() || list.size != 2) return
        val userID = list[0]
        val userPassword = list[1]
        loginModel.login(userID, userPassword).subscribe({
            Log.d(TAG, "账号密码自动登录成功")
        }, {
            SPUtil.getInstance().putString(Constants.TOKEN,"")
            Token.instance.refresh()
            Log.e(TAG, "账号密码自动登录失败 ${it.message}")
        })
    }

    fun checkToken() {
        Token.instance.refresh()
        if (Token.instance.isLogin()) { //sp文件中有缓存的token //验证token是否过期
            Token.instance.accessToken?.let {
                loginModel.checkToken(it)
                    .subscribe({ tokenMsg ->
                        if (!TextUtils.isEmpty(tokenMsg.message) && tokenMsg.message.equals("令牌有效")) {
                            // 登录成功
                        } else {
                            autoLogin()
                        }
                    }, {
                        autoLogin()
                    })
            }

        } else {
            autoLogin()
        }

    }
}
