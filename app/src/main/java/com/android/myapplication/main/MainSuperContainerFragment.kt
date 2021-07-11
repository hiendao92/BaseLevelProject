package com.android.myapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.myapplication.R
import com.android.myapplication.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author at-hien.dao
 */
@AndroidEntryPoint
class MainSuperContainerFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_super_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(MainFragment(), false)
    }

    override fun onBindViewModel() {

    }
}
