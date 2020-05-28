package example.level.superlevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.BaseFragment
import com.example.myapplication.R

class SuperExampleContainerFragment : BaseFragment() {
    override fun onBindViewModel() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_super_container, container, false)
    }

}
