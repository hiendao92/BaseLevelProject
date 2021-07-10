package com.android.myapplication.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import kotlinx.android.synthetic.main.fragment_test_child_vp.*

class TestChildFromViewPagerFragment : BaseFragment() {
    override fun onBindViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_child_vp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLevel.text = "Level : ${level}"
        initListeners()
    }

    private fun initListeners() {
        btnAddFragment.setOnClickListener {
            addFragment(TestFragment.newInstance("Add Fragment", page = 1))
        }
        btnReplaceFragment.setOnClickListener {
            replaceFragment(
                TestFragment.newInstance("Replace Fragment", page = 1),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddFragmentWithViewPager.setOnClickListener {
            addFragment(TestViewPagerFragment())
        }
        btnReplaceFragmentWithViewPager.setOnClickListener {
            replaceFragment(
                TestViewPagerFragment(),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddSuperFragment.setOnClickListener {
            addSuperFragment(TestFragment.newInstance("Add Super Fragment"))
        }
        btnAddFragmentInChild.setOnClickListener {
            addInChildFragment(TestFragment.newInstance("Fragment in child "))
        }
    }

}
