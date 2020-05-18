package level.toplevel

import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import data.AppConstant
import test.Tab1Fragment
import test.Tab2Fragment
import test.Tab3Fragment
import test.Tab4Fragment

class MainContainerAdapter(fm: FragmentManager, private val level: Int) :
    FragmentStatePagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
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

    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): Fragment? {
        return registeredFragments.get(position)
    }

}

enum class EPageContainer(val value: Int) {
    PAGE1(0),
    PAGE2(1),
    PAGE3(2),
    PAGE4(3),
}