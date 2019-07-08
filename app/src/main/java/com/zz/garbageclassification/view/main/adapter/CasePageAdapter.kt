package com.zz.garbageclassification.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.lang.NullPointerException

/**
 * <p> 文件描述 : 首页 - 待办 - 签名案件和待办的 左右滑动adapter<p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/26 <p>
 * <p> 更改时间 : 2019/3/26 <p>
 * <p> 版本号 : 1 <p>
 *
 */
class CasePageAdapter(fm :FragmentManager,mFragments: List<Fragment>,mTitles:Array<String>) : FragmentPagerAdapter(fm) {
    lateinit var mFragments: List<Fragment>
    lateinit var mTitles :Array<String>

    init {
        if (mFragments == null || mFragments.isEmpty()) throw NullPointerException("mFragments 不能为空")
        this.mFragments = mFragments
        this.mTitles = mTitles

    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}