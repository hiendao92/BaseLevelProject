package com.android.myapplication.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import kotlinx.android.synthetic.main.fragment_test.btnAddFragment
import kotlinx.android.synthetic.main.fragment_test.btnAddFragmentWithViewPager
import kotlinx.android.synthetic.main.fragment_test.btnAddSuperFragment
import kotlinx.android.synthetic.main.fragment_test.btnReplaceFragment
import kotlinx.android.synthetic.main.fragment_test.btnReplaceFragmentWithViewPager
import kotlinx.android.synthetic.main.fragment_test_tab.*

class Tab3Fragment : BaseFragment() {
    override fun onBindViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        tvTop.text = "Page 3"
    }

    private fun initListeners() {
        btnAddFragment.setOnClickListener {
            addFragment(TestFragment.newInstance("Add Fragment 3", page = 3))
        }
        btnReplaceFragment.setOnClickListener {
            replaceFragment(
                TestFragment.newInstance("Replace Fragment 3", page = 3),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddFragmentWithViewPager.setOnClickListener {
            addFragment(TestFragment.newInstance("Add Fragment", true))
        }
        btnReplaceFragmentWithViewPager.setOnClickListener {
            replaceFragment(
                TestFragment.newInstance("Replace Fragment", true),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddSuperFragment.setOnClickListener {
            addSuperFragment(TestFragment.newInstance("Add Super Fragment"))
        }
    }
}
