package com.zz.garbageclassification.view.main

import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.arbitratorapp.view.custom.YJToast
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.BaseActivity
import com.zz.garbageclassification.base.contract.main.FeedbackContract
import com.zz.garbageclassification.model.bean.enums.Grabage
import com.zz.garbageclassification.presenter.main.FeedbackPresenter
import com.zz.garbageclassification.util.StringUtil
import kotlinx.android.synthetic.main.activity_feedback.*
class FeedbackActivity : BaseActivity() ,FeedbackContract.View{
    private val mPresenter by lazy { FeedbackPresenter() }
    private var grabageList :MutableList<String> =  mutableListOf(
        Grabage.RECY.gName,
        Grabage.DRY.gName,
        Grabage.HARMFUL.gName,
        Grabage.WET.gName)
    private var pvOptions: OptionsPickerView<String>? = null    //选择框


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attchView(this)
        setContentView(R.layout.activity_feedback)
        pvOptions = OptionsPickerBuilder(this, object : OnOptionsSelectListener {
            override fun onOptionsSelect(options1: Int, options2: Int, options3: Int, v: View?) {
                tvPickType.setText(grabageList[options1])
            }

        })
            .setTitleText("选择分类")
            .setOutSideCancelable(true)
            .build()
        pvOptions?.apply {
            setPicker(grabageList)
        }

        tvPickType.setOnClickListener {
            pvOptions?.show()
        }

        btnSubmit.setOnClickListener{
            var garName = etGarName?.getText()?.toString()?:"";
            if (StringUtil.isEmpty(garName)) {
                mPresenter.commitGarbageClass(garName,tvPickType.getText().toString())
            } else {
                YJToast.makeErrorText(this,"请输入垃圾名称", YJToast.LENGTH_SHORT).show()
            }

        }
    }

    override fun commitError(errorMess: String) {
        YJToast.makeText(this,"提交失败!!!", YJToast.LENGTH_SHORT).show()
        tvThanks.setText("感谢您宝贵的意见!!!")
    }

    override fun commitSucc(grabage: String) {
        YJToast.makeText(this,"提交成果!!!", YJToast.LENGTH_SHORT).show()
        tvThanks.setText("非常感谢您的帮助,您服务是我们最大的荣耀")
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unAttchView()
    }
}
