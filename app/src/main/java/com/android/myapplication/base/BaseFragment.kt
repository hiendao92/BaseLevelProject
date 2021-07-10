package com.android.myapplication.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : FragmentController() {

    internal fun addSuperFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (activity as? BaseMainActivity)?.addSuperFragment(
            fragment,
            isEnableAnim,
            tagNameBackStack
        )
    }
}
