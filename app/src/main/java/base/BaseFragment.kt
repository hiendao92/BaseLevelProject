package base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import data.AppConstant
import main.MainActivity

abstract class BaseFragment : Fragment() {

    private var level: Int = 0

    internal fun getLevel() = level

    internal fun isContainer() = arguments?.getBoolean(AppConstant.KEY_IS_CONTAINER) ?: false

    internal fun setLevel(level: Int) {
        this.level = level
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    abstract fun onBindViewModel()

    internal fun replaceFragment(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (fragment as? BaseFragment)?.setLevel(getLevel())
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        val fm: FragmentManager? =
            when (getLevel()) {
                AppConstant.LEVEL_TOP -> fragmentManager
                AppConstant.LEVEL_CONTAINER -> {
                    if (isContainer())
                        childFragmentManager
                    else
                        fragmentManager
                }
                else -> {
                    var parentFm: FragmentManager? = fragmentManager
                    for (index in 0..range) {
                        parentFm = parentFragment?.fragmentManager
                    }
                    parentFm
                }
            }
        fm?.beginTransaction()?.apply {
            if (isEnableAnim) {

                setCustomAnimations(
                    R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left,
                    R.anim.anim_slide_enter_from_left, R.anim.anim_slide_out_to_right
                )
            }
            replace(R.id.flContainer, fragment, fragment.javaClass.simpleName)
            if (isAddBackStack) {
                addToBackStack(tagNameBackStack)
            }
            commit()
        }
    }

    internal fun addFragment(
        fragment: Fragment, isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (fragment as? BaseFragment)?.setLevel(getLevel())
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        val fm: FragmentManager? =
            when (getLevel()) {
                AppConstant.LEVEL_TOP -> fragmentManager
                AppConstant.LEVEL_CONTAINER -> {
                    if (isContainer())
                        childFragmentManager
                    else
                        fragmentManager
                }
                else -> {
                    var parentFm: FragmentManager? = fragmentManager
                    for (index in 0..range) {
                        parentFm = parentFragment?.fragmentManager
                    }
                    parentFm
                }
            }
        fm?.beginTransaction()?.apply {
            if (isEnableAnim) {
                setCustomAnimations(
                    R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left,
                    R.anim.anim_slide_enter_from_left, R.anim.anim_slide_out_to_right
                )
            }
            add(R.id.flContainer, fragment, fragment.javaClass.simpleName)
            addToBackStack(tagNameBackStack)
            commit()
        }
    }

    internal fun addSuperFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (activity as? MainActivity)?.addSuperFragment(
            fragment,
            isEnableAnim,
            tagNameBackStack
        )
    }

}
