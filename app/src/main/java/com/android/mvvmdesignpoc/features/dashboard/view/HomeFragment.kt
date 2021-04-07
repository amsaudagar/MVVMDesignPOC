package com.android.mvvmdesignpoc.features.dashboard.view

import android.os.Bundle
import android.view.View
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.core.platform.BaseFragment

/**
 * Represents the fragment for home screen
 */
class HomeFragment : BaseFragment() {

    override fun layoutId() = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}