package com.android.mvvmdesignpoc.core.navigation

import android.content.Context
import android.content.Intent
import com.android.mvvmdesignpoc.features.dashboard.view.HomeActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {

    /**
     * Displays the country details screen
     */
    fun showCountryDetails(context: Context) = context.startActivity(Intent(context, HomeActivity::class.java))
}