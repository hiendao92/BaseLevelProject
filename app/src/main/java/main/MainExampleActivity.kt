package main

import base.BaseMainActivity
import base.level.maintoplevel.BaseMainContainer
import base.level.superlevel.BaseSuperContainer
import example.level.maintoplevel.MainExampleContainerFragment
import example.level.superlevel.SuperExampleContainerFragment

class MainExampleActivity : BaseMainActivity() {
    override fun mainContainer(): BaseMainContainer = MainExampleContainerFragment()

    override fun superContainer(): BaseSuperContainer = SuperExampleContainerFragment()

}
