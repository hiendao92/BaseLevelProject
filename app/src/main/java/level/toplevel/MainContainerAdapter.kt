package level.toplevel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import base.BaseAdapterPager
import data.AppConstant
import example.Tab1Fragment
import example.Tab2Fragment
import example.Tab3Fragment
import example.Tab4Fragment

class MainContainerAdapter(fm: FragmentManager, private val level: Int) :
    BaseAdapterPager(fm, level) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            EPageContainer.PAGE1.value -> Tab1Fragment().apply {
                // set container
                arguments = Bundle().apply {
                    putBoolean(AppConstant.KEY_IS_CONTAINER, true)
                }
                setLevel(level + 1)
            }
            EPageContainer.PAGE2.value -> Tab2Fragment().apply {
                // set container
                arguments = Bundle().apply {
                    putBoolean(AppConstant.KEY_IS_CONTAINER, true)
                }
                setLevel(level + 1)
            }
            EPageContainer.PAGE3.value -> Tab3Fragment().apply {
                // set container
                arguments = Bundle().apply {
                    putBoolean(AppConstant.KEY_IS_CONTAINER, true)
                }
                setLevel(level + 1)
            }
            EPageContainer.PAGE4.value -> Tab4Fragment().apply {
                // set container
                arguments = Bundle().apply {
                    putBoolean(AppConstant.KEY_IS_CONTAINER, true)
                }
                setLevel(level + 1)
            }
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