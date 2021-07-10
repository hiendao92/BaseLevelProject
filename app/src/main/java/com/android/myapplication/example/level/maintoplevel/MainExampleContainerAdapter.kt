package com.android.myapplication.example.level.maintoplevel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.myapplication.base.BaseAdapterPager

class MainExampleContainerAdapter(
    fm: FragmentManager,
    level: Int,
    private val fragments: MutableList<Fragment>
) :
    BaseAdapterPager(fm, level) {
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = 4

}

enum class EPageContainer(val value: Int) {
    PAGE1(0),
    PAGE2(1),
    PAGE3(2),
    PAGE4(3),
}