package base

import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import data.AppConstant

abstract class BaseAdapterPager(fm: FragmentManager, private val level: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as BaseFragment
        fragment.apply {
            // set container
            if (level == AppConstant.LEVEL_TOP) {
                arguments = Bundle().apply {
                    putBoolean(AppConstant.KEY_IS_CONTAINER, true)
                }
            }
            setLevel(level + 1)
        }
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