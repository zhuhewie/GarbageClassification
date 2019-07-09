package com.zz.garbageclassification.view.main

import android.os.Bundle
import com.zz.garbageclassification.model.bean.enums.Grabage
import com.zz.garbageclassification.R
import com.zz.garbageclassification.base.BaseActivity
import kotlinx.android.synthetic.main.activity_expain.*

class ExpainActivity : BaseActivity() {

    companion object {

        public var TYPE = "type"

    }

    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expain)
        intent?.extras?.apply {
            type = getString(TYPE)?:""
        }

        initView()
    }

    fun initView() {
        when (type) {
            Grabage.RECY.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_recycly_garbage)
                tvGarbage.setText(Grabage.RECY.gName+"是指")
                tvInclude.setText(Grabage.RECY.gName+"主要包括")
                tvRequire.setText(Grabage.RECY.gName+"投放要求")
                tvIncludeAll.setText("酱油瓶、玻璃杯、平板玻璃、易拉罐、饮料瓶、 洗发水瓶、塑料玩具、书本、报纸、广告单、 纸板箱、衣服、床上用品等")
                tvGarbageEx.setText("适宜回收利用和资源化利 用的，如：玻、金、塑、 纸、衣")
                tvRequireContext.setText(
                            "• 轻投轻放\n" +
                            "• 清洁干燥，避免污染\n"+
                            "• 废纸尽量平整\n"+
                            "• 立体包装物请清空内容物，清洁后压扁投放\n"+
                            "• 有尖锐边角的，应包裹后投放\n"
                )
                tvGarbage.setTextColor(getResources().getColor(R.color.color_recy))
                tvInclude.setTextColor(getResources().getColor(R.color.color_recy))
                tvRequire.setTextColor(getResources().getColor(R.color.color_recy))
                tvIncludeAll.setTextColor(getResources().getColor(R.color.color_recy))
                tvGarbageEx.setTextColor(getResources().getColor(R.color.color_recy))
                tvRequireContext.setTextColor(getResources().getColor(R.color.color_recy))
            }
            Grabage.HARMFUL.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_harmful_garbage)
                tvGarbage.setText(Grabage.HARMFUL.gName+"是指")
                tvInclude.setText(Grabage.HARMFUL.gName+"主要包括")
                tvRequire.setText(Grabage.HARMFUL.gName+"投放要求")
                tvGarbageEx.setText("对人体健康或者自然环境造成直接或潜在危害的废弃物" +
                        "有害垃圾主要包括废电池、油漆桶、荧光灯管、废药品及其包装物等")
                tvIncludeAll.setText("废电池、油漆桶、荧光灯管、废药品及其包装物等")
                tvRequireContext.setText(
                            "• 投放时请注意轻放\n" +
                            "• 易破损的请连带包装或包裹后轻放\n" +
                            "• 如易挥发，请密封后投放\n"
                )
                tvGarbage.setTextColor(getResources().getColor(R.color.color_harmful))
                tvInclude.setTextColor(getResources().getColor(R.color.color_harmful))
                tvRequire.setTextColor(getResources().getColor(R.color.color_harmful))
                tvIncludeAll.setTextColor(getResources().getColor(R.color.color_harmful))
                tvGarbageEx.setTextColor(getResources().getColor(R.color.color_harmful))
                tvRequireContext.setTextColor(getResources().getColor(R.color.color_harmful))

            }
            Grabage.DRY.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_dry_garbage)
                tvGarbage.setText(Grabage.DRY.gName+"是指")
                tvInclude.setText(Grabage.DRY.gName+"主要包括")
                tvRequire.setText(Grabage.DRY.gName+"投放要求")
                tvGarbageEx.setText("除有害垃圾、可回收物、 湿垃圾以外的其他生活废弃物")
                tvIncludeAll.setText("餐盒、餐巾纸、湿纸巾、卫生间用纸、塑料袋、 食品包装袋、污染严重的纸、烟蒂、纸尿裤、 一次性杯子、大骨头、贝壳、花盆、陶瓷等")
                tvRequireContext.setText(
                    "• 尽量沥干水分\n" +
                            "• 难以辨识类别的生活垃圾投入干垃圾容器内\n"
                )

                tvGarbage.setTextColor(getResources().getColor(R.color.color_dry))
                tvInclude.setTextColor(getResources().getColor(R.color.color_dry))
                tvRequire.setTextColor(getResources().getColor(R.color.color_dry))
                tvIncludeAll.setTextColor(getResources().getColor(R.color.color_dry))
                tvGarbageEx.setTextColor(getResources().getColor(R.color.color_dry))
                tvRequireContext.setTextColor(getResources().getColor(R.color.color_dry))
            }
            Grabage.WET.gName -> {
                imgGarbage.setImageResource(R.mipmap.ic_wet_garbage)
                tvGarbage.setText(Grabage.WET.gName+"是指")
                tvInclude.setText(Grabage.WET.gName+"主要包括")
                tvRequire.setText(Grabage.WET.gName+"投放要求")
                tvGarbageEx.setText("日常生活垃圾产生的容易腐烂的生物质废弃物")
                tvIncludeAll.setText("剩菜剩饭、瓜皮果核、花卉绿植、过期食品等")
                tvRequireContext.setText("剩菜剩饭、瓜皮果核、花卉绿植、过期食品等")
                tvRequireContext.setText(
                    "• 纯流质的食物垃圾，如牛奶等，应直接倒进下水口\n" +
                    "• 有包装物的湿垃圾应将包装物去除后分类投放，包装物请投放到对应的可回收物或干垃圾容器\n"
                )

                tvGarbage.setTextColor(getResources().getColor(R.color.color_wet))
                tvInclude.setTextColor(getResources().getColor(R.color.color_wet))
                tvRequire.setTextColor(getResources().getColor(R.color.color_wet))
                tvIncludeAll.setTextColor(getResources().getColor(R.color.color_wet))
                tvGarbageEx.setTextColor(getResources().getColor(R.color.color_wet))
                tvRequireContext.setTextColor(getResources().getColor(R.color.color_wet))
            }
        }
    }
}
