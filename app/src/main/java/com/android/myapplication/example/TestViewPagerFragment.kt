package com.android.myapplication.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import kotlinx.android.synthetic.main.fragment_test_vp.*

class TestViewPagerFragment : BaseFragment() {
    private val adapter by lazy {
        TestAdapterPager(childFragmentManager, level)
    }

    override fun onBindViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_vp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        vpContainer.apply {
            adapter = this@TestViewPagerFragment.adapter
        }
    }

    private fun initListeners() {
        btnTab1.setOnClickListener {
            vpContainer.currentItem = 0
        }
        btnTab2.setOnClickListener {
            vpContainer.currentItem = 1
        }

    }
}