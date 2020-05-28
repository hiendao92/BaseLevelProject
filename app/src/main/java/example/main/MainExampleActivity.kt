package example.main

import base.BaseMainActivity
import example.level.maintoplevel.MainExampleContainerFragment
import example.level.superlevel.SuperExampleContainerFragment

class MainExampleActivity : BaseMainActivity() {
    override fun mainContainer() = MainExampleContainerFragment()

    override fun superContainer() = SuperExampleContainerFragment()

}
