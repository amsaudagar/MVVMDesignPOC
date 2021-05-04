package com.android.mvvmdesignpoc.features.dashboard.view

import com.android.mvvmdesignpoc.core.platform.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Represents the activity for home screen
 */
@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun init() {}

    override fun fragment() : HomeFragment {
        return HomeFragment()
    }
}