package base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import base.level.maintoplevel.BaseMainContainer
import base.level.superlevel.BaseSuperContainer
import com.example.myapplication.R
import data.AppConstant

abstract class BaseMainActivity : AppCompatActivity() {
    private lateinit var mainContainer: BaseMainContainer
    private lateinit var superContainer: BaseSuperContainer

    abstract fun mainContainer(): BaseMainContainer

    abstract fun superContainer(): BaseSuperContainer

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
            // set container
            arguments = Bundle().apply {
                putBoolean(AppConstant.KEY_IS_CONTAINER, true)
            }
            setLevel(AppConstant.LEVEL_CONTAINER)
        }
    }

    internal fun addSuperFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        superContainer.addFragmentFromSuper(fragment, isEnableAnim, tagNameBackStack)
    }

    /**
     * handle fragment
     */
    internal fun addFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (isEnableAnim) {
                setCustomAnimations(
                    R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left,
                    R.anim.anim_slide_enter_from_left, R.anim.anim_slide_out_to_right
                )
            }
            add(R.id.flContainer, fragment, fragment.javaClass.simpleName)
            addToBackStack(tagNameBackStack)
            commit()
        }
    }

    internal fun replaceFragment(
        fragment: Fragment,
        isAddBackStack: Boolean,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (isEnableAnim) {
                setCustomAnimations(
                    R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left,
                    R.anim.anim_slide_enter_from_left, R.anim.anim_slide_out_to_right
                )
            }
            replace(R.id.flContainer, fragment, fragment.javaClass.simpleName)
            if (isAddBackStack) {
                addToBackStack(tagNameBackStack)
            }
            commit()
        }
    }
}
