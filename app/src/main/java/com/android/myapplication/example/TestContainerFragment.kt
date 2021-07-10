package com.android.myapplication.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R

class TestContainerFragment : BaseFragment() {
    companion object {
        private const val KEY_TAB = "key_tab"
        internal fun newInstance(tab: Int) = TestContainerFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_TAB, tab)
            }
        }
    }

    private var currentFragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_super_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleAddChildFragment()
    }

    private fun handleAddChildFragment() {
        if (currentFragment == null) {
            currentFragment = TestChildFromViewPagerFragment()
            currentFragment?.also {
                replaceInContainer(it, false, false)
            }
        }
    }

    override fun onBindViewModel() {

    }
}