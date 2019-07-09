package com.zz.garbageclassification.view.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.arbitratorapp.view.custom.YJToast
import com.zz.garbageclassification.model.bean.enums.Grabage
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.BaseEditActivity
import com.zz.garbageclassification.base.contract.main.MainContract
import com.zz.garbageclassification.model.bean.response.GarbageResponse
import com.zz.garbageclassification.presenter.main.MainPresenter
import com.zz.garbageclassification.util.StringUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseEditActivity() , MainContract.View{
    private val mPresenter by lazy { MainPresenter() }

    private var isResult = false
    var garbage = Grabage.OTHER
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attchView(this)
        setContentView(R.layout.activity_main)
        addClick()
    }

    /**
     *
     */
    private fun addClick() {
        btnClassif.setOnClickListener {
            var grabageName : String = etGarbage?.getText()?.toString()?:""
            if (StringUtil.isEmpty(grabageName)) {
                YJToast.makeErrorText(this,"请输入垃圾名称",YJToast.LENGTH_SHORT).show()
            } else {
                mPresenter.getGarbageClass(grabageName)
            }
        }

        //输入垃圾名字
        etGarbage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                checkResult(s?.toString()?:"")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        //反馈
        question.setOnClickListener {
            startActivity(Intent(this@MainActivity,FeedbackActivity::class.java))
        }

        imgGarbage.setOnClickListener {
            startEx()
        }
        tvResult.setOnClickListener {
            startEx()
        }

    }

    private fun startEx() {
        if (isResult) {
            val intents = Intent(this@MainActivity, ExpainActivity::class.java)
            intents.putExtra(ExpainActivity.TYPE, garbage.gName)
            startActivity(intents)
        }
    }

    private fun checkResult(grabageName :String) {
        when (grabageName) {
            Grabage.RECY.gName -> {
                garbage=Grabage.RECY
                imgGarbage.setImageResource(R.mipmap.ic_recycly_garbage)
            }
            Grabage.HARMFUL.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_harmful_garbage)
                garbage=Grabage.HARMFUL

            }
            Grabage.DRY.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_dry_garbage)
                garbage=Grabage.DRY

            }
            Grabage.WET.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_wet_garbage)
                garbage=Grabage.WET

            }
        }

    }

    override fun getError(errorMess: String) {
        YJToast.makeErrorText(this,"不能识别这个垃圾:${errorMess}",YJToast.LENGTH_SHORT).show()

    }

    override fun getSucc(grabage : GarbageResponse) {
        YJToast.makeErrorText(this,"识别成功",YJToast.LENGTH_SHORT).show()
        tvResult.setText(grabage.getName())
        checkResult(grabage.catName)
        isResult=true

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unAttchView()
    }
}
