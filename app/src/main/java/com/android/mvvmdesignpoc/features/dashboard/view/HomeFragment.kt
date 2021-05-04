package com.android.mvvmdesignpoc.features.dashboard.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.core.exception.Failure
import com.android.mvvmdesignpoc.core.extension.failure
import com.android.mvvmdesignpoc.core.extension.observe
import com.android.mvvmdesignpoc.core.platform.BaseFragment
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.CountryDetailsResponse
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.Row
import com.android.mvvmdesignpoc.features.dashboard.viewmodel.CountryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Represents the fragment for home screen
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment(), CountryDetailsAdapter.IOnItemSelectListener {

    companion object {
        const val REFRESH_TIME = 20000 // 20 secs
        const val ROW_ITEM = "ROW_ITEM"
    }
    private var timeStamp = System.currentTimeMillis()

    private val countryDetailsViewModel: CountryDetailsViewModel by viewModels()

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

    override fun onRowItemSelected(row: Row) {
        val bundle = Bundle()
        bundle.putParcelable(ROW_ITEM, row)
        findNavController().navigate(R.id.action_homeFragment_to_countryFeatureFragment, bundle)
    }

    private fun handleCountryDetailsSuccess(countryDetails: CountryDetailsResponse?) {
        hideProgressDialog()
        //Removes all the elements with all empty or null details
        countryDetails?.rows?.apply {
            countryDetails.rows.removeIf { it.title.isNullOrEmpty() &&
                    it.description.isNullOrEmpty()
                    && it.imageHref.isNullOrEmpty()}
        }
        rvCountryDetails.adapter = CountryDetailsAdapter(requireActivity(),
            countryDetails?.rows?: arrayListOf(), this)

        txtScreenTitle.text = countryDetails?.title?:""

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