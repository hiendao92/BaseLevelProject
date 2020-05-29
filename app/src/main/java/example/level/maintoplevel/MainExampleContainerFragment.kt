package example.level.maintoplevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.BaseFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_main_container.*

class MainExampleContainerFragment : BaseFragment() {

    private val adapter: MainExampleContainerAdapter by lazy {
        MainExampleContainerAdapter(childFragmentManager, level)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_container, container, false)
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
            offscreenPageLimit = 4
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

}
