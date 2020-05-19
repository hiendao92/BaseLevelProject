package example.level.maintoplevel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import base.BaseAdapterPager
import example.Tab1Fragment
import example.Tab2Fragment
import example.Tab3Fragment
import example.Tab4Fragment

class MainExampleContainerAdapter(fm: FragmentManager, private val level: Int) :
    BaseAdapterPager(fm, level) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            EPageContainer.PAGE1.value -> Tab1Fragment()
            EPageContainer.PAGE2.value -> Tab2Fragment()
            EPageContainer.PAGE3.value -> Tab3Fragment()
            EPageContainer.PAGE4.value -> Tab4Fragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int = 4

}

enum class EPageContainer(val value: Int) {
    PAGE1(0),
    PAGE2(1),
    PAGE3(2),
    PAGE4(3),
}