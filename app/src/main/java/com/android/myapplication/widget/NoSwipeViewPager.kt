package com.android.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

class NoSwipeViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : androidx.viewpager.widget.ViewPager(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun canScrollHorizontally(direction: Int): Boolean = false
}
