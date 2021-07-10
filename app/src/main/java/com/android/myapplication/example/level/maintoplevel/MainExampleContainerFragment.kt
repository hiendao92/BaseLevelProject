package com.android.myapplication.example.level.maintoplevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.myapplication.base.BaseFragment
import com.android.myapplication.R
import com.android.myapplication.example.containertab.ContainerTabExampleFragment
import kotlinx.android.synthetic.main.fragment_main_container.*

class MainExampleContainerFragment : BaseFragment() {

    private val adapter: MainExampleContainerAdapter by lazy {
        MainExampleContainerAdapter(childFragmentManager, level, fragments)
    }

    private val fragments: MutableList<Fragment> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_container, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    override fun onBindViewModel() {
    }

    private fun initViews() {
        vpContainer.apply {
            adapter = this@MainExampleContainerFragment.adapter
            offscreenPageLimit = EPageContainer.values().size
        }
        bottomNavigation.apply {
            setOnNavigationItemSelectedListener {
                vpContainer.currentItem = it.order
                true
            }
        }
    }

    private fun initFragments() {
        EPageContainer.values().forEachIndexed { index, _ ->
            fragments.add(ContainerTabExampleFragment.newInstance(index))
        }
    }

    private fun initListeners() {

    }

}
