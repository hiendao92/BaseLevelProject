package example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.BaseFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_tab4.*

class Tab4Fragment : BaseFragment() {
    override fun onBindViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {

    }

    private fun initListeners() {
        btnAddFragment.setOnClickListener {
            addFragment(TestFragment.newInstance("Add Fragment 4", page = 4))
        }
        btnReplaceFragment.setOnClickListener {
            replaceFragment(
                TestFragment.newInstance("Replace Fragment 4", page = 4),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddFragmentWithViewPager.setOnClickListener {
            addFragment(TestFragment.newInstance("Add Fragment", true))
        }
        btnReplaceFragmentWithViewPager.setOnClickListener {
            replaceFragment(
                TestFragment.newInstance("Replace Fragment", true),
                isEnableAnim = true,
                isAddBackStack = true
            )
        }
        btnAddSuperFragment.setOnClickListener {
            addSuperFragment(TestFragment.newInstance("Add Super Fragment"))
        }
    }
}
