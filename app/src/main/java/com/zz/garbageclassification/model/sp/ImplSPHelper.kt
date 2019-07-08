package com.zz.garbageclassification.model.sp

import android.content.Context
import android.content.SharedPreferences
import com.zz.garbageclassification.Constants
import com.zz.garbageclassification.app.App

class ImplSPHelper  constructor() :SpHelper {

    private var mSp:SharedPreferences? = null
    init {
        mSp = App.instance.getSharedPreferences(Constants.ARBITRATOR_SP_NAME, Context.MODE_PRIVATE)
    }
}