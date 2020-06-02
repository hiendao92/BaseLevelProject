package base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import data.AppConstant
import extensions.addFragment
import extensions.replaceFragment

abstract class BaseMainActivity : AppCompatActivity() {
    private lateinit var mainContainer: BaseFragment
    private lateinit var superContainer: BaseFragment

    abstract fun mainContainer(): BaseFragment

    abstract fun superContainer(): BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBase()
        setContentView(R.layout.activity_main)
        replaceFragment(mainContainer, false, isEnableAnim = false)
        addFragment(superContainer, false)
    }

    private fun initBase() {
        mainContainer = mainContainer()
        superContainer = superContainer().apply {
            setLevel(AppConstant.LEVEL_CONTAINER)
        }
    }

    internal fun addSuperFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        superContainer.addInContainer(fragment, isEnableAnim, tagNameBackStack)
    }

}
