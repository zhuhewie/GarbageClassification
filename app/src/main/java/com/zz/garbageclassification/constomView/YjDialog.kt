package com.arbitratorapp.view.custom

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

import com.zz.garbageclassification.R
import kotlinx.android.synthetic.main.dialog_sign.*

private const val TITLE_TEXT = "title_text"

/**
 * 项目中通用提示框
 *
 */
class YjDialog : DialogFragment() {
    private var titleText: String? = null
    private var listenter :OnClickListenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleText = arguments?.getString(TITLE_TEXT)?:"是否确认!"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_sign, container, false)
        tvDialogTitle.setText(titleText)
        return view
    }

    fun setOnClicKListenter(listenter :OnClickListenter){
        this.listenter = listenter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCancle.setOnClickListener {
            listenter?.apply {
                cancleClickListenter()
            }
            dismiss()
        }
        tvOk.setOnClickListener {
            listenter?.apply {
                okClickListenter()
            }
            dismiss()
        }


    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    interface OnClickListenter{
        fun okClickListenter()
        fun cancleClickListenter()
    }

    companion object {
        /**
         *
         */
        @JvmStatic
        fun newInstance(titleText : String) =
            YjDialog().apply {
                arguments = Bundle().apply {
                    putString(TITLE_TEXT, titleText)
                }
            }
    }
}
