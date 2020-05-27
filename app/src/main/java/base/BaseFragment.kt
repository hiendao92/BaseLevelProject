package base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import data.AppConstant

abstract class BaseFragment : Fragment() {

    private var level: Int = 0

    internal fun getLevel() = level

    private var callBackWhenBackPress: OnBackPressedCallback = object : OnBackPressedCallback(
        true
        /** true means that the callback is enabled */
    ) {
        override fun handleOnBackPressed() {
            handleBackPressed()
        }
    }

    internal fun setLevel(level: Int) {
        this.level = level
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // note that you could enable/disable the callback here as well by setting callback.isEnabled = true/false
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callBackWhenBackPress
        )
    }

    private fun handleAddCallBack(isEnable: Boolean = true) {
        callBackWhenBackPress.isEnabled = isEnable
    }

    override fun onPause() {
        super.onPause()
        handleAddCallBack(false)
    }

    protected open fun handleBackPressed() {
        when (getLevel()) {
            AppConstant.LEVEL_TOP, AppConstant.LEVEL_CONTAINER -> return
            AppConstant.LEVEL_TAB -> {
                fragmentManager?.popBackStack()
            }
            else -> {
                if (getLevel() % 2 == 0) {
                    // child fragment in viewpager
                    fragmentManager?.also {
                        if (it.backStackEntryCount > 0) {
                            // pop in child viewpager
                            it.popBackStack()
                        } else {
                            // child in viewpager size == 0
                            // pop in parent
                            (parentFragment as? BaseFragment)?.handleBackPressed()
                        }
                    }

                } else {
                    // container
                    (parentFragment as? BaseFragment)?.handleBackPressed()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handleAddCallBack(true)
        onBindViewModel()
    }

    abstract fun onBindViewModel()

    internal fun replaceFragment(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        when (getLevel()) {
            // do not use fragment manager of activity
            AppConstant.LEVEL_TOP -> return
            AppConstant.LEVEL_CONTAINER -> {
                // add in main tab folow
                replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> {
                // get fragment manager of fragment when level = 1(Main top container)
                var parentFm: Fragment? = this
                for (index in 1..range) {
                    parentFm = parentFm?.parentFragment
                }
                // user fragment with level = 1 to add fragment
                (parentFm as? BaseFragment)?.replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
        }
    }

    /**
     * add fragment in main tab follow
     */

    internal fun addFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        when (getLevel()) {
            // do not use fragment manager of activity
            AppConstant.LEVEL_TOP -> return
            AppConstant.LEVEL_CONTAINER -> {
                // add in main tab folow
                addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> {
                // get fragment manager of fragment when level = 1(Main top container)
                var parentFm: Fragment? = this
                for (index in 1..range) {
                    parentFm = parentFm?.parentFragment
                }
                // user fragment with level = 1 to add fragment
                (parentFm as? BaseFragment)?.addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
        }
    }

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

    /**
     * add fragment in viewpager
     */
    internal fun addInChildFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        when {
            // do not use fragment manager of activity
            level == AppConstant.LEVEL_TOP -> return
            level == AppConstant.LEVEL_CONTAINER || level % 2 != 0 -> {
                // in this base: container level is a odd number
                addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            level == AppConstant.LEVEL_TAB || level % 2 == 0 -> {
                // child in viewpager
                (parentFragment as? BaseFragment)?.addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> return
        }
    }

    /**
     * replace fragment in viewpager
     */
    internal fun replaceInChildFragment(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        when {
            // do not use fragment manager of activity
            level == AppConstant.LEVEL_TOP -> return
            level == AppConstant.LEVEL_CONTAINER || level % 2 != 0 -> {
                // in this base: container level is a odd number
                replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            level == AppConstant.LEVEL_TAB || level % 2 == 0 -> {
                // child in viewpager
                (parentFragment as? BaseFragment)?.replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> return
        }
    }

    internal fun replaceInContainer(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (fragment as? BaseFragment)?.setLevel(getLevel() + 1)
        childFragmentManager.beginTransaction().apply {
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

    internal fun addInContainer(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (fragment as? BaseFragment)?.setLevel(getLevel() + 1)
        childFragmentManager.beginTransaction().apply {
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
}
