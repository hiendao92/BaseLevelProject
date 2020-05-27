package example

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import base.BaseAdapterPager

class TestAdapterPager(fm: FragmentManager, level: Int) : BaseAdapterPager(fm, level) {
    override fun getItem(position: Int): Fragment = TestContainerFragment()

    override fun getCount(): Int = 2
}
