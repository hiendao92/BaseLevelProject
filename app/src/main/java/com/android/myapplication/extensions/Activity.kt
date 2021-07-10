package com.android.myapplication.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.myapplication.R

fun AppCompatActivity.addFragment(
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

fun AppCompatActivity.replaceFragment(
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
