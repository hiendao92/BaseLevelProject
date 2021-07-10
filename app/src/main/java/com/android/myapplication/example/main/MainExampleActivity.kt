package com.android.myapplication.example.main

import com.android.myapplication.base.BaseMainActivity
import com.android.myapplication.example.level.maintoplevel.MainExampleContainerFragment
import com.android.myapplication.example.level.superlevel.SuperExampleContainerFragment

class MainExampleActivity : BaseMainActivity() {
    override fun mainContainer() = MainExampleContainerFragment()

    override fun superContainer() = SuperExampleContainerFragment()

}
