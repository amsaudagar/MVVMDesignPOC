package com.android.mvvmdesignpoc.features.dashboard.view

import com.android.mvvmdesignpoc.core.platform.BaseActivity

/**
 * Represents the activity for home screen
 */
class HomeActivity : BaseActivity() {

    override fun init() {}

    override fun fragment() : HomeFragment {
        return HomeFragment()
    }
}