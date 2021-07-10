package com.android.myapplication.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment() {

    private var page: Int = 1

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_VIEWPAGER = "viewpager"
        private const val KEY_PAGE = "viewpager"
        internal fun newInstance(
            text: String = "",
            isWithViewPager: Boolean = false,
            page: Int = 1
        ) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TEXT, text)
                    putBoolean(KEY_VIEWPAGER, isWithViewPager)
                    putInt(KEY_PAGE, page)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onBindViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.also {
            page = it.getInt(KEY_PAGE)
        }
        initViews()
        initListeners()
    }

    private fun initViews() {
        tvCenter.text = arguments?.getString(KEY_TEXT)
        tvPage.text = "Page :$page ----- Level :${level}"
    }

    private fun initListeners() {
        btnAddFragment.setOnClickListener {
            addFragment(newInstance("Add Fragment", page = page))
        }
        btnReplaceFragment.setOnClickListener {
            replaceFragment(
                newInstance("Replace Fragment", page = page),
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
            addSuperFragment(newInstance("Add Super Fragment"))
        }
    }
}
