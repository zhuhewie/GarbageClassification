package com.zz.garbageclassification.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.zz.garbageclassification.Grabage
import com.zz.garbageclassification.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addClick()
    }

    /**
     *
     */
    private fun addClick() {

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
    }

    private fun checkResult(grabageName :String) : Grabage {
        return when (grabageName) {
            Grabage.recy.name->{
                Grabage.recy
            }
            Grabage.harmful.name->{
                Grabage.harmful
            }
            Grabage.dry.name->{
                Grabage.dry
            }
            Grabage.wet.name->{
                Grabage.wet
            }
            else ->{
                Grabage.other
            }
        }

    }
}
