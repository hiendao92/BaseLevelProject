package base.level.superlevel

import androidx.fragment.app.Fragment
import base.BaseFragment

abstract class BaseSuperContainer : BaseFragment() {

    internal fun addFragmentFromSuper(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        addFragment(fragment, isEnableAnim, tagNameBackStack)
    }
}
