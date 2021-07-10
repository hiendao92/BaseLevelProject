package com.android.myapplication.example.containertab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import com.android.myapplication.example.Tab1Fragment
import com.android.myapplication.example.Tab2Fragment
import com.android.myapplication.example.Tab3Fragment
import com.android.myapplication.example.Tab4Fragment
import com.android.myapplication.example.level.maintoplevel.EPageContainer

class ContainerTabExampleFragment : BaseFragment() {
    companion object {
        private const val KEY_TAB = "key_tab"
        internal fun newInstance(tab: Int) = ContainerTabExampleFragment().apply {
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
            arguments?.also {
                currentFragment = when (it.getInt(KEY_TAB)) {
                    EPageContainer.PAGE1.value -> {
                        Tab1Fragment()
                    }
                    EPageContainer.PAGE2.value -> {
                        Tab2Fragment()
                    }
                    EPageContainer.PAGE3.value -> {
                        Tab3Fragment()
                    }
                    EPageContainer.PAGE4.value -> {
                        Tab4Fragment()
                    }
                    else -> {
                        null
                    }
                }
            }
            currentFragment?.also {
                replaceInContainer(it, isAddBackStack = false, isEnableAnim = false)
            }
        }
    }

    override fun onBindViewModel() = Unit
}
