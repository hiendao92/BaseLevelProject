package main

import android.os.Bundle
import androidx.fragment.app.Fragment
import base.BaseActivity
import base.BaseFragment
import com.example.myapplication.R
import data.AppConstant
import level.superlevel.SuperContainerFragment
import level.toplevel.MainContainerFragment

class MainActivity : BaseActivity() {

    private var mainContainer: Fragment = MainContainerFragment()
    private var superContainer: Fragment = SuperContainerFragment().apply {
        // set container
        arguments = Bundle().apply {
            putBoolean(AppConstant.KEY_IS_CONTAINER, true)
        }
        setLevel(AppConstant.LEVEL_CONTAINER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(mainContainer, false, isEnableAnim = false)
        addFragment(superContainer, false)
    }

    override fun onBackPressed() {
        superContainer.childFragmentManager.also {
            if (it.fragments.size > 0) {
                // pop super flow
                it.popBackStack()
            } else {
                // pop main flow
                (mainContainer as? MainContainerFragment)?.popBackStack()
            }
        }
    }

    internal fun addSuperFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        (superContainer as? BaseFragment)?.addFragment(fragment, isEnableAnim, tagNameBackStack)
    }

}
