package base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import data.AppConstant

abstract class BaseFragment : Fragment() {

    private var level: Int = 0

    internal fun getLevel() = level

    private fun isContainer() = arguments?.getBoolean(AppConstant.KEY_IS_CONTAINER) ?: false

    internal fun setLevel(level: Int) {
        this.level = level
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(
            true
            /** true means that the callback is enabled */
        ) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        }

        // note that you could enable/disable the callback here as well by setting callback.isEnabled = true/false
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    protected open fun handleBackPressed() {
        when (getLevel()) {
            AppConstant.LEVEL_TOP -> return
            AppConstant.LEVEL_CONTAINER -> {
                if (isContainer()) {
                    return
                } else {
                    fragmentManager?.popBackStack()
                }
            }
            else -> {
                if (isContainer()) {
                    (parentFragment as? BaseFragment)?.handleBackPressed()
                } else {
                    fragmentManager?.popBackStack()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    abstract fun onBindViewModel()

    internal fun replaceFragment(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null,
        replaceInChild: Boolean = false
    ) {
        (fragment as? BaseFragment)?.setLevel(AppConstant.LEVEL_CONTAINER)
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        val fm: FragmentManager? =
            when (getLevel()) {
                // do not use fragment manager of activity
                AppConstant.LEVEL_TOP -> return
                AppConstant.LEVEL_CONTAINER -> {
                    if (isContainer())
                        childFragmentManager
                    else
                        fragmentManager
                }
                else -> {
                    if (replaceInChild) {
                        // get child fragment manager of container
                        var parentFm: BaseFragment? = this
                        while (parentFm?.isContainer() == false) {
                            parentFm = parentFm.parentFragment as? BaseFragment
                        }
                        parentFm?.childFragmentManager
                    } else {
                        // get fragment manager of fragment when level = 1(Main top container)
                        var parentFm: Fragment? = this
                        for (index in 1..range) {
                            parentFm = parentFragment
                        }
                        // set level child = parent
                        (fragment as? BaseFragment)?.setLevel(getLevel())
                        parentFm?.fragmentManager
                    }
                    // or use activity
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
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null,
        addInChild: Boolean = false
    ) {
        (fragment as? BaseFragment)?.setLevel(AppConstant.LEVEL_CONTAINER)
        val range = getLevel() - AppConstant.LEVEL_CONTAINER
        val fm: FragmentManager? =
            when (getLevel()) {
                // do not use fragment manager of activity
                AppConstant.LEVEL_TOP -> return
                AppConstant.LEVEL_CONTAINER -> {
                    if (isContainer())
                        childFragmentManager
                    else
                        fragmentManager
                }
                else -> {
                    if (addInChild) {
                        // get child fragment manager of container
                        var parentFm: BaseFragment? = this
                        while (parentFm?.isContainer() == false) {
                            parentFm = parentFm.parentFragment as? BaseFragment
                        }
                        // set level child = parent
                        (fragment as? BaseFragment)?.setLevel(getLevel())
                        parentFm?.childFragmentManager
                    } else {
                        // get fragment manager of fragment when level = 1(Main top container)
                        var parentFm: Fragment? = this
                        for (index in 1..range) {
                            parentFm = parentFragment
                        }
                        parentFm?.fragmentManager
                    }
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
        (activity as? BaseMainActivity)?.addSuperFragment(
            fragment,
            isEnableAnim,
            tagNameBackStack
        )
    }

}
