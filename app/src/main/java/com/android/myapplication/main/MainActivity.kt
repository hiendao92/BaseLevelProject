package com.android.myapplication.main

import com.android.myapplication.base.BaseFragment
import com.android.myapplication.base.BaseMainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author at-hien.dao
 */
class MainActivity : BaseMainActivity() {

    override fun mainContainer(): BaseFragment = MainContainerFragment()

    override fun superContainer(): BaseFragment = MainSuperContainerFragment()
}
