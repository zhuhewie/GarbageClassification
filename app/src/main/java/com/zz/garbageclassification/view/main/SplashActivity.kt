package com.zz.garbageclassification.view.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.RootActivity
import com.zz.garbageclassification.model.http.model.LoginModel
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

                startMain()
            }
        }
    }

    fun startMain() {
        val disposable = Observable.timer(3, SECONDS)
            .subscribe(Consumer {
                var intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
