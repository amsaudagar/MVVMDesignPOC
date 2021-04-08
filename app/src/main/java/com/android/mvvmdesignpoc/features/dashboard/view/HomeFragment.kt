package com.android.mvvmdesignpoc.features.dashboard.view

import android.os.Bundle
import android.view.View
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.extension.failure
import com.android.mvvmdesignpoc.core.extension.observe
import com.android.mvvmdesignpoc.core.platform.BaseFragment
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import com.android.mvvmdesignpoc.features.dashboard.viewmodel.CountryDetailsViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Represents the fragment for home screen
 */
class HomeFragment : BaseFragment() {

    companion object {
        const val REFRESH_TIME = 20000 // 20 secs
    }
    private var timeStamp = System.currentTimeMillis()

    private val countryDetailsViewModel: CountryDetailsViewModel by viewModel()

    override fun layoutId() = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(countryDetailsViewModel) {
            observe(countryDetails, ::handleCountryDetailsSuccess)
            failure(failure, ::handleFailure)
        }

        showProgressDialog()
        countryDetailsViewModel.getCountryDetails()

        swipeRefreshLayout.setOnRefreshListener {
            // check if 45 secs have passed and load the balances
            val elapsedSecs = System.currentTimeMillis() - timeStamp
            if (elapsedSecs >= REFRESH_TIME) {
                swipeRefreshLayout.isRefreshing = true
                showProgressDialog()
                countryDetailsViewModel.getCountryDetails()
            } else {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun handleCountryDetailsSuccess(countryDetails: CountryDetailsResponse?) {
        hideProgressDialog()
        rvCountryDetails.adapter = CountryDetailsAdapter(requireActivity(), countryDetails?.rows?: arrayListOf())

        txtTitle.text = countryDetails?.title?:""

        swipeRefreshLayout.isRefreshing = false
        timeStamp = System.currentTimeMillis()
    }

    private fun handleFailure(failure: Failure?) {
        hideProgressDialog()

        when (failure) {
            is Failure.NetworkConnection -> renderFailure(getString(R.string.connection_failure))
            is Failure.ServerError -> renderFailure(getString(R.string.connection_failure))
            else -> {

            }
        }
    }
}