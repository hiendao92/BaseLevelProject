package level.toplevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.BaseFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_container.*

class MainContainerFragment : BaseFragment() {

    private val adapter: MainContainerAdapter by lazy {
        MainContainerAdapter(childFragmentManager, getLevel()).apply {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    override fun onBindViewModel() {
    }

    private fun initViews() {
        vpContainer.apply {
            adapter = this@MainContainerFragment.adapter
        }
        bottomNavigation.apply {
            setOnNavigationItemSelectedListener {
                vpContainer.currentItem = it.order
                true
            }
        }
    }

    private fun initListeners() {

    }

    internal fun popBackStack() {
        vpContainer?.currentItem?.also {
            adapter.getRegisteredFragment(it)?.childFragmentManager?.popBackStack()
        }
    }
}
