package example.level.maintoplevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.level.maintoplevel.BaseMainContainer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_container.*

class MainExampleContainerFragment : BaseMainContainer() {

    private val adapter: MainExampleContainerAdapter by lazy {
        MainExampleContainerAdapter(childFragmentManager, getLevel())
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
            adapter = this@MainExampleContainerFragment.adapter
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

    override fun popBackStack() {
        vpContainer?.currentItem?.also {
            adapter.getRegisteredFragment(it)?.childFragmentManager?.popBackStack()
        }
    }

}
