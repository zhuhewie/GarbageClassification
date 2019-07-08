package com.zz.garbageclassification.view.main.dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

import com.zz.garbageclassification.R
import com.zz.garbageclassification.model.bean.response.AddressResponse
import kotlinx.android.synthetic.main.dialog_sign.*

private const val ARG_ADDRESS = "address"

/**
 * 确认签名的dialog
 *
 */
class SignDialog : DialogFragment() {
    private var address: AddressResponse? = null
    private var listenter :OnClickListenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            address = it.getParcelable<AddressResponse>(ARG_ADDRESS)
//        } ?: dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_sign, container, false)
//        dialog?.window?.apply {
//            setBackgroundDrawableResource(R.color.trans)
//            decorView.setPadding(0,0,0,0)
//            attributes = attributes?.apply {
//                gravity = Gravity.CENTER
//                width = WindowManager.LayoutParams.WRAP_CONTENT
//                height = WindowManager.LayoutParams.WRAP_CONTENT
//            }
//        }
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
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddressSelectDialog.
         */
        @JvmStatic
        fun newInstance() =
            SignDialog().apply {
                arguments = Bundle().apply {
//                    putParcelable(ARG_ADDRESS, address)
                }
            }
    }
}
